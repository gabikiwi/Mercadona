package com.mercadona.pos.camel.saf;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

import com.mercadona.pos.camel.saf.message.MercadonaMessage;
import com.mercadona.pos.camel.saf.message.routing.DirectRoutingBuilder;

/**
 * Hello world!
 *
 */
public class DirectRoutingMainApp {
	public static void main(String[] args) {

		try {
			Main main = new Main();
			main.enableHangupSupport();

			RouteBuilder routeBuilder = new DirectRoutingBuilder();
			main.addRouteBuilder(routeBuilder);

			main.start();

			ProducerTemplate producerTemplate = routeBuilder.getContext().createProducerTemplate();
			System.out.println("Direct routing ");
			producerTemplate.sendBody("direct:start", new MercadonaMessage());

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
