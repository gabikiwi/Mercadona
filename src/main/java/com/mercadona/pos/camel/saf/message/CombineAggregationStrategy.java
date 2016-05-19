package com.mercadona.pos.camel.saf.message;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class CombineAggregationStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExhange, Exchange newExchange) {
		// TODO Auto-generated method stub
		
		if (oldExhange == null) {
			return newExchange;
		}
		
		else {
			
			MercadonaMessage mercadona_message = oldExhange.getIn().getBody(MercadonaMessage.class);
			String value = newExchange.getIn().getBody(String.class);
			String property = newExchange.getIn().getHeader("property", String.class);
			
			if ("weighing_device_id".equals(property)) {
				mercadona_message.setWeighing_device_id(Long.parseLong(value));
			}
			
			else if  ("message_id".equals(property)) {
				mercadona_message.setMessage_id(value);
			}
			
			else if  ("message_data".equals(property)) {
				mercadona_message.setMessage_data(value);
			}
			
			else if ("weighing_time".equals(property)) {
				mercadona_message.setWeighing_time(value);
			}
			return oldExhange;
		}
		
		
		
	}

}
