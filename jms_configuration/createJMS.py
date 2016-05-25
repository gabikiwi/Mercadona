"""
This script starts an edit session, creates two different JMS Servers, 
targets the jms servers to the server WLST is connected to and creates
jms topics, jms queues and jms templates in a JMS System module. The 
jms queues and topics are targeted using sub-deployments. 
"""

import sys
from java.lang import System


print "Starting the script ..."
connect('weblogic','welcome1','t3://localhost:7001')
edit()
startEdit()

servermb=getMBean("Servers/myserver")
if servermb is None:
    print 'Value is Null'

else:
    jmsserver1mb = create('MercadonaJMSServer1','JMSServer')
    jmsserver1mb.addTarget(servermb)
    jmsserver2mb = create('MercadonaJMSServer2','JMSServer')
    jmsserver2mb.addTarget(servermb)

    jmsMercadonaSystemResource = create("MercadonaJMSSystemResource","JMSSystemResource")
    jmsMercadonaSystemResource.addTarget(servermb)

    subDep1mb = jmsMercadonaSystemResource.createSubDeployment('DeployToJMSServer1')
    subDep1mb.addTarget(jmsserver1mb)
    subDep2mb = jmsMercadonaSystemResource.createSubDeployment('DeployToJMSServer2')
    subDep2mb.addTarget(jmsserver2mb)

    theJMSResource = jmsMercadonaSystemResource.getJMSResource()

    connfact1 = theJMSResource.createConnectionFactory('MercadonaCF1')
    connfact1.setJNDIName('jms.MercadonaCF1')
    connfact1.setSubDeploymentName('DeployToJMSServer1')
    connfact2 = theJMSResource.createConnectionFactory('MercadonaCF2')
    connfact2.setJNDIName('jms.MercadonaCF2')
    connfact2.setSubDeploymentName('DeployToJMSServer2')

    print "Creating MercadonaQueue1..."
    jmsqueue1 = theJMSResource.createQueue('MercadonaQueue1')
    jmsqueue1.setJNDIName('jms.MercadonaJMSQueue1')
    jmsqueue1.setSubDeploymentName('DeployToJMSServer1')

    print "Creating MercadonaQueue2..."
    jmsqueue2 = theJMSResource.createQueue('MercadonaQueue2')
    jmsqueue2.setJNDIName('jms.MercadonaJMSQueue2')
    jmsqueue2.setSubDeploymentName('DeployToJMSServer2')

    print "Creating MercadonaTopic1..."
    jmstopic1 = theJMSResource.createTopic("MercadonaTopic1")
    jmstopic1.setJNDIName('jms.MercadonaJMSTopic1')
    jmstopic1.setSubDeploymentName('DeployToJMSServer1')

    print "Creating MercadonaTopic2..."
    jmstopic2 = theJMSResource.createTopic("MercadonaTopic2")
    jmstopic2.setJNDIName('jms.MercadonaJMSTopic2')
    jmstopic2.setSubDeploymentName('DeployToJMSServer2')

    print "Creating MercadonaJMSTemplate..."
    jmstemplate = theJMSResource.createTemplate('MercadonaJMSTemplate')
    jmstemplate.setMaximumMessageSize(20)    

try:
    save()
    activate(block="true")
    print "script returns SUCCESS"   
except:
    print "Error while trying to save and/or activate!!!"
    dumpStack()