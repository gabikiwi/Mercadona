package com.mercadona.pos.saf.message.processor;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MessageProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("Processing of the mercadona message started");
		String myString = exchange.getIn().getBody(String.class);
		
		String[] myArray = myString.split(System.getProperty("line.separator"));
		StringBuffer sb = new StringBuffer();
		for (String s : myArray) {

			sb.append(s).append(",");
		}

		StringBuilder builder = new StringBuilder();
		
		try (BufferedReader reader = new BufferedReader(new FileReader("data/source/mercadona.txt"))) {
			System.out.println("test - inside buffer reader");
			final String lineSeparator = System.getProperty("line.separator");
			while (true) {
				String line = reader.readLine();

				if (line == null) {
					break;
				} else {
					builder.append(line).append(lineSeparator);
				}
			}
			reader.close();
		}
		
		catch (Exception e) {
			System.out.println("reader este null");
		}
	System.out.println("test - create new exchange object");	
	exchange.getIn().setBody(builder.toString().replace(":", " = "));
	//exchange.getIn().setBody(sb.toString().replace(":", " = "));
		
	}

}
