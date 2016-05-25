###############################################################################################
# WLST script to create JMS Server, FileStore, System Resource, Connection Factory and Queue
#
# The script uses the -loadProperties command to load in the properties for the script.
# Edit the loadProperties("jms.properties") parameter with the relative path.
# java weblogic.WLST create.py
#
###############################################################################################
#================================
# Properties File Location
#================================
# If the properties file is external to this script, prepend the filename with the relative path.
properties = loadProperties("jmsCreate.properties")

#================================
# Properties File Mappings
#================================
username = v_username
password = v_password
url = v_url
targetServer = v_targetServer
jmsServerName = v_jmsServerName
jmsSystemResource = v_jmsSystemResource
jmsSystemResourceTargetTotal = v_moduleTargetTotal
jmsSystemResourceTargetServers = v_moduleTargetServers
jmsSystemResourceTargetServerTypes = v_moduleTargetServerTypes
factoryName = v_factoryName
factoryJNDI = v_factoryJNDI
queueName = v_queueName
queueJNDI = v_queueJNDI
mySubDepName = v_mySubDepName
fileStoreName = v_fileStoreName
fileStoreDirectory = v_fileStoreDirectory

#================================
# Connect
#================================
connect(username, password, url)

#================================
# Start Session
#================================

edit()
startEdit()
print "Starting the script ..."

#================================
# Set MBeans
#================================
servermb = getMBean('/Servers/'+targetServer)
defTargetMB = getMBean("/JMSServers/"+jmsSystemResource)

#================================
# Create Filestore
#================================
print 'Creating File Store'
fsMB = getMBean("/FileStores/"+fileStoreName)
if fsMB is None:
 cd('/')
 cmo.createFileStore(fileStoreName)
 cd('/FileStores/'+fileStoreName)
 cmo.setDirectory(fileStoreDirectory)
# Single node server (Admin) example. Production will be Clustered
 set('Targets',jarray.array([ObjectName('com.bea:Name='+targetServer+',Type=Server')], ObjectName))
 print "*** Created Filestore"
else: 
 print "FileStore already exists... Skipping"
#================================
# Creating JMS Server
#================================
print "Creating JMS Server..."
cd('/')
serverInstanceMBean = getMBean("/Deployments/"+jmsServerName)
if serverInstanceMBean is None:
 jmsserver1mb = create(jmsServerName,'JMSServer')
 print "Creating JMS Server Target"
 jmsserver1mb.addTarget(servermb)
 print "Set Persistent Store..."
 cd('/JMSServers/'+jmsServerName)
 cmo.setPersistentStore(getMBean('/FileStores/'+fileStoreName))
 print "*** JMS Server Created"
else:
 print "*** JMS Server already exists... Skipping"

#================================
# Creating JMS Module
#================================
cd('/')
jmsSR = getMBean("/JMSSystemResources/"+jmsSystemResource)
if jmsSR is None:
 jmsModule = create(jmsSystemResource, "JMSSystemResource")
 cd('/JMSSystemResources/'+jmsSystemResource)
 arrayTargetServers = jmsSystemResourceTargetServers.split(",")
 arrayTargetTypes = jmsSystemResourceTargetServerTypes.split(",")
 targetObjectArray = []
 print "Total number of module targets:- "+jmsSystemResourceTargetTotal
 for i in range(0,int(jmsSystemResourceTargetTotal)):
  tempServer = arrayTargetServers[i]
  print "tempServer = "+tempServer
  tempType = arrayTargetTypes[i]
  print "tempServerType = "+tempType
  tempTargetString = 'com.bea:Name='+tempServer+',Type='+tempType
  targetObjectArray.append(ObjectName(tempTargetString))
 set('Targets',jarray.array(targetObjectArray, ObjectName)) 
 print "*** Created JMS Module ***"
else:
 jmsModule=getMBean("/JMSSystemResources/"+jmsSystemResource)
 jmsResource = jmsModule.getJMSResource()
 print "*** JMS Module already exists... Skipping"

#================================
# Creating SubDeployment
#================================
print "*** Creating SubDeployment"
jmsSubDeployment=getMBean("/JMSSystemResources/"+jmsSystemResource+"/SubDeployments/"+mySubDepName)
if jmsSubDeployment is None:
 jmsSubDeployment=jmsModule.createSubDeployment(mySubDepName)
else:
 print "SubDeployment already exists... skipping"
 
#================================
# Set SubDeployment targets
#================================
print "*** SubDeployment targeting ***"
jmsSubDeployment=getMBean("/JMSSystemResources/"+jmsSystemResource+"/SubDeployments/"+mySubDepName)
if jmsSubDeployment is None:
 print "No SubDeployment to exists to perform targeting"
else:
 cd('/SystemResources/'+jmsSystemResource+'/SubDeployments/'+mySubDepName)
 set('Targets',jarray.array([ObjectName('com.bea:Name='+jmsServerName+',Type=JMSServer')], ObjectName))
#================================
# Creating Connection Factory - This code can be duplicated if multiple connection factories are required
#================================
print "Creating Connection Factory..."
newConnectionFactory = getMBean("/JMSSystemResources/"+jmsSystemResource+"/JMSResource/"+jmsSystemResource+"/ConnectionFactories/"+factoryName)
if newConnectionFactory is None:
 jmsResource = getMBean('/JMSSystemResources/'+jmsSystemResource+'/JMSResource/'+jmsSystemResource)
 newConnectionFactory = jmsResource.createConnectionFactory(factoryName)
 newConnectionFactory.setJNDIName(factoryJNDI)
 #newConnectionFactory.setSubDeploymentName(mySubDepName) Uncomment if default targetting is not applicable
 newConnectionFactory.setDefaultTargetingEnabled(true)
 newConnectionFactory.transactionParams.setXAConnectionFactoryEnabled(false)
else: 
 print "*** Connection Factory already exists... Skipping"

#================================
# Creating JMS Queue - This code can be duplicated if multiple queues are required
#================================
# print "Creating JMS Queue..."

newQueue = getMBean("/JMSSystemResources/"+jmsSystemResource+"/JMSResource/"+jmsSystemResource+"/Queues/"+queueName)
if newQueue is None:
 newQueue = jmsResource.createQueue(queueName)
 newQueue.setJNDIName(queueJNDI)
 newQueue.setSubDeploymentName(mySubDepName)
else: 
 print "*** JMS Queue already exists... Skipping"
#================================
# Script Completed - Activate changes
#================================
activate()
dumpStack()
print "**** JMS Resources Created. Restart the Server to apply changes *****"

exit()