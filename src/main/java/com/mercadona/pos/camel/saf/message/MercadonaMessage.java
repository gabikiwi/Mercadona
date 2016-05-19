package com.mercadona.pos.camel.saf.message;

import java.text.MessageFormat;

public class MercadonaMessage {

	private long weighing_device_id;
	private String message_id;

	// private MercadonaMessageData message_data;
	private String message_data;
	private String weighing_time;

	public long getWeighing_device_id() {
		return weighing_device_id;
	}

	public void setWeighing_device_id(long value) {
		this.weighing_device_id = value;
	}

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public String getMessage_data() {
		return message_data;
	}

	public void setMessage_data(String message_data) {
		this.message_data = message_data;
	}

	public String getWeighing_time() {
		return weighing_time;
	}

	public void setWeighing_time(String weighing_time) {
		this.weighing_time = weighing_time;
	}

	@Override
	public String toString() {
		return MessageFormat.format("Balance Number: {0}, message number: {1}, message content: {2}, weighing time: {3}",
				weighing_device_id, message_id, message_data, weighing_time);
	}

}
