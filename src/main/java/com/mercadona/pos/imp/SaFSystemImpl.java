package com.mercadona.pos.imp;

import com.mercadona.pos.interf.SaFSystem;
import com.mercadona.pos.message.Message;
import com.mercadona.pos.message.MessageType;
import com.mercadona.pos.message.Priority;
import com.mercadona.pos.message.TimeToLeave;

public class SaFSystemImpl implements SaFSystem {
	
	private static SaFSystem instance;
	
	private SaFSystemImpl () {
		
	}

	// singelton pattern
	public static synchronized SaFSystem getInstance() {
		
		if (instance == null) {
		
			 instance = new SaFSystemImpl();
		
		
		}
		
		return instance;
	}

	public static void setInstance(SaFSystem instance) {
		SaFSystemImpl.instance = instance;
	}

	@Override
	public Integer getTotalMessagesStoredCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCurrentMessagesStored() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCurrentMessagesStored(MessageType messageType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getMessagesForwardCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalErrorsCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCurrentInErrorModule() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCurrentInErrorModule(MessageType messageType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetTotalMessagesStoredCount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetMessagesForwardCount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetTotalErrorCount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String sendMessage(MessageType messageType, Message message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendMessage(MessageType messageType, Message message, TimeToLeave ttl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendMessage(MessageType messageType, Message message, Priority priorty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendMessage(MessageType messageType, Message message, TimeToLeave ttl, Priority priorty) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
