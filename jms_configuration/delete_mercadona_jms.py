###############################################################################################
# WLST script to delete JMS components
#
# The script uses the -loadProperties command to load in the properties for the script.
# Edit the loadProperties("jms.properties") parameter with the relative path.
# java weblogic.WLST delete.py
#
###############################################################################################
#================================
# Set Properties
#================================
# If the properties file is external to this script, prepend the filename with the relative path.
properties = loadProperties("delete_mercadona_jms.properties")

username = v_username
password = v_password
url = v_url
jmsServerName = v_jmsServerName
jmsSystemResource = v_jmsSystemResource
factoryName = v_factoryName
queueName = v_queueName
mySubDepName = v_mySubDepName
fileStoreName = v_fileStoreName

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
serverMBean=getMBean("/Deployments/")
moduleMBean=getMBean("/JMSSystemResources/")
sdMBean=getMBean("/JMSSystemResources/"+jmsSystemResource+"/SubDeployments/")
cfMBean=getMBean("/JMSSystemResources/"+jmsSystemResource+"/JMSResource/"+jmsSystemResource+"/ConnectionFactories/")
queueMBean=getMBean("/JMSSystemResources/"+jmsSystemResource+"/JMSResource/"+jmsSystemResource+"/Queues/")
jmsserver1mb=getMBean("/JMSServers/"+jmsServerName)

#================================  
# Deleting Connection Factory
#================================  
print "Deleting Connection Factory..."

cfInstanceMBean=getMBean("/JMSSystemResources/"+jmsSystemResource+"/JMSResource/"+jmsSystemResource+"/ConnectionFactories/"+factoryName)
if cfInstanceMBean != None:
                cfMBean.destroyConnectionFactory(cfInstanceMBean)  
                print "*** Connection Factory Deleted"
else:
                print "*** No Factory to delete.... Skipping"

#================================  
# Deleting JMS Queue
#================================  
print "Deleting JMS Queue..."

qInstanceMBean=getMBean("/JMSSystemResources/"+jmsSystemResource+"/JMSResource/"+jmsSystemResource+"/Queues/"+queueName)
if qInstanceMBean != None:
                queueMBean.destroyQueue(qInstanceMBean)
                print "*** JMS Queue Deleted"
else:
                print "*** No JMS Queue to delete.... Skipping"

#================================  
# Delete SubDeployment
#================================  
print "Deleting SubDeployment..."
sdInstanceMBean=getMBean("/JMSSystemResources/"+jmsSystemResource+"/SubDeployments/"+mySubDepName)
if sdInstanceMBean != None:
                sdMBean.destroySubDeployment(sdInstanceMBean)
                print "*** JMS SubDeployment Deleted"
else:
                print "*** No SubDeployment to delete.... Skipping"

#================================  
# Delete JMS Module
#================================
print "Deleting JMS Module..."

moduleInstanceMBean=getMBean("/JMSSystemResources/"+jmsSystemResource)
if moduleInstanceMBean != None:
                moduleMBean.destroyJMSSystemResource(moduleInstanceMBean)
                print "*** JMS Module Deleted"
else:
                print "*** No JMS Module to delete.... Skipping"
    
#================================
# Delete JMS Server
#================================
print "Deleting JMS Server..."

#cmo.destroyJMSServer(getMBean('/Deployments/1JMSServer'))

serverInstanceMBean=getMBean("/Deployments/"+jmsServerName)
if serverInstanceMBean != None:
                serverMBean.destroyJMSServer(serverInstanceMBean)
                print "*** JMS Server Deleted"
else:
                print "*** No JMS Server to delete.... Skipping"

#================================
# Delete Filestore
#================================
print "Deleting JMS FileStore..."
############################# To refactor
try: 
                cmo.destroyFileStore(getMBean('/FileStores/'+fileStoreName))
                print "*** JMS Filestore Deleted"

except:
                print "No Filestore to delete"

#############################

activate()
dumpStack()
print "**** JMS Resources Deleted *****"

exit()