package com.mercadona.pos.camel.saf.message.routing;

import org.apache.camel.builder.RouteBuilder;

public class XpathRouteBuilder extends RouteBuilder{
		
		/**
	     * Let's configure the Camel routing rules using Java code...
	     */

		@Override
		public void configure() throws Exception {
			
			// here is a sample which processes the input files
	        // (leaving them in place - see the 'noop' flag)
	        // then performs content based routing on the message using XPath
	        from("file:data/xml?noop=true")
	            .choice()
	                .when(xpath("/MercadonaMessage/weighing_device_id = 101"))
	                    .log("messages coming from weighing device with id 101 ")
	                    .to("file:data/destination/101/")
	                
	                 .when(xpath("/MercadonaMessage/weighing_device_id = 102"))
	                    .log("messages coming from weighing device with id 102 ")
	                    .to("file:data/destination/102/")
	                
	                .otherwise()
	                
	                    .log("messages coming from other weighing device ")
	                    .to("file:data/destination/others/");
	        
	        
		}
}
