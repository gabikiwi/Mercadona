package com.mercadona.pos.saf.message.routing;

import org.apache.camel.builder.RouteBuilder;

import com.mercadona.pos.saf.message.CombineAggregationStrategy;

public class DirectRoutingBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:start")
		.multicast()
		.to("direct:print",
				"direct:aggregate",
				"direct:Weighing_device_id_Enricher",
				"direct:Message_id_Enricher",
				"direct:Message_data_Enricher",
				"direct:Timestamp_Enricher");
		
		from("direct:print").to("stream:out");
		
		from("direct:Weighing_device_id_Enricher")
		.setHeader("property", constant("weighing_device_id"))
		.setBody(constant("111"))
		.to("direct:aggregate");
		
		from("direct:Message_id_Enricher")
		.setHeader("property", constant("message_id"))
		.setBody(constant("10000111"))
		.to("direct:aggregate");
		
		from("direct:Message_data_Enricher")
		.setHeader("property", constant("message_data"))
		.setBody(constant("1200 g grapes"))
		.to("direct:aggregate");
		
		from("direct:Timestamp_Enricher")
		.setHeader("property", constant("weighing_time"))
		.setBody(constant("2016-05-16 23:59:59"))
		.to("direct:aggregate");
		
		from("direct:aggregate")
		.aggregate(constant(true),
				new CombineAggregationStrategy())
		.completionSize(5)
		.to("stream:out");

	}

}
