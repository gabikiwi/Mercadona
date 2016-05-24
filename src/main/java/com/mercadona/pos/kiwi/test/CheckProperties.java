package com.mercadona.pos.kiwi.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
 
public class CheckProperties {
	
	public static void main(String[] args) {
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			
			input  = new FileInputStream("configuration/Mercadona.saf.properties");
			
			// load a properties file
			prop.load(input);

			// get the property value and print it out
			
			System.out.println(prop.getProperty("configuration.pooling.seconds"));
			System.out.println(prop.getProperty("configuration.connectionTimeout"));
			System.out.println(prop.getProperty("ticket.messageDestination.CF"));
			System.out.println(prop.getProperty("ticket.messageDestination.Queue"));
			System.out.println(prop.getProperty("ticket.message.default.TTL"));
			System.out.println(prop.getProperty("ticket.message.default.Priority"));
			System.out.println(prop.getProperty("ticket.message.default.MaxRetryCount"));

			// save properties to project root folder
			

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	  }

}
