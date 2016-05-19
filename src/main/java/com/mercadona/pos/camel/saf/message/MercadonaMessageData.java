package com.mercadona.pos.camel.saf.message;

import java.sql.Time;
import java.text.MessageFormat;

public class MercadonaMessageData {
	
	private String messageID;	
	private String itemID;	
	private String itemName;	
	private String receiptID;
	private Time timestamp_receipt;
	private long value_receipt;
	
	// Message Sample
		@Override
		public String toString() {
			return MessageFormat.format(
					"messageID: {0} ; itemID: {1}, itemName: {2}, receiptID: {3}, timestamp_receipt: {4}, value_receipt: {5}",
					messageID,itemID,itemName,receiptID,timestamp_receipt,timestamp_receipt,value_receipt);
		}
	
	public String getMessageID() {
		return messageID;
	}



	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}



	public String getItemID() {
		return itemID;
	}



	public void setItemID(String itemID) {
		this.itemID = itemID;
	}



	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public String getReceiptID() {
		return receiptID;
	}



	public void setReceiptID(String receiptID) {
		this.receiptID = receiptID;
	}



	public Time getTimestamp_receipt() {
		return timestamp_receipt;
	}



	public void setTimestamp_receipt(Time timestamp_receipt) {
		this.timestamp_receipt = timestamp_receipt;
	}



	public long getValue_receipt() {
		return value_receipt;
	}



	public void setValue_receipt(long value_receipt) {
		this.value_receipt = value_receipt;
	}




	

	
	
}
