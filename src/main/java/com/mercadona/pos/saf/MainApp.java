package com.mercadona.pos.saf;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

import com.mercadona.pos.saf.message.routing.Input;
import com.mercadona.pos.saf.message.routing.XpathRouteBuilder;


public class MainApp {
	
	public static void main(String... args)  throws Exception {
		
		//routing based on file input
       /* Main main = new Main();
        main.enableHangupSupport();
        
        RouteBuilder routeBuilder = new Input();
        main.addRouteBuilder(routeBuilder);
        
        System.out.println("messages routed to the location /data/destination ");
        
        System.out.println("Starting Camel. Use ctrl + c to terminate the JVM.\n");
        main.run(args);
      */
        
		// routing based on value using xpath and xml file input
        Main main1 = new Main();
        main1.enableHangupSupport();
        
        
        RouteBuilder routeBuilder1 = new XpathRouteBuilder();
        main1.addRouteBuilder(routeBuilder1);
        
        System.out.println("xml messages baased on value routed to the location /data/destination ");	
        main1.run(args);
        
        
    }

}
