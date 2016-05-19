package com.mercadona.pos.saf.message.routing;

import org.apache.camel.builder.RouteBuilder;

import com.mercadona.pos.saf.message.processor.MessageProcessor;

public class Input extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		System.out.println("My Routing Started");
		from("file:data/source?noop=true").process(new MessageProcessor()).to(
				"file:data/destination?fileName=mercadona_output.txt");
		System.out.println("My Routing complete");
		
	}

}
