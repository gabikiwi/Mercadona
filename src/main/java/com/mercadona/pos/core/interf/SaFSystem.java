package com.mercadona.pos.core.interf;

import com.mercadona.pos.message.Message;
import com.mercadona.pos.message.MessageType;
import com.mercadona.pos.message.Priority;
import com.mercadona.pos.message.TimeToLeave;

public interface SaFSystem {
	
	// get the info from counter in memory
	public Integer getTotalMessagesStoredCount ();
	
	// get the info from counter in memory
	public Integer getCurrentMessagesStored ();
	
	// get the info from counter from DDBB
	public Integer getCurrentMessagesStored (MessageType messageType);

	// get the info from counter in memory
    public Integer getMessagesForwardCount ();
    
	// get the info from counter in memory
    public Integer getTotalErrorsCount ();
    
    // None:count from DDBB
    public Integer getCurrentInErrorModule ();

    // None:count from DDBB + where
    public Integer getCurrentInErrorModule (MessageType messageType);

    // TotalMessagesStored = getCurrent MessagesStored()
    public void resetTotalMessagesStoredCount ();

    // MessagesForwardCount = 0
    public void resetMessagesForwardCount ();
	
    // TotalErrorCount = getCurrentErrorInErrorModule()
    public void resetTotalErrorCount ();
    
	// Return a error code (0000 – No error)
	public String sendMessage (MessageType messageType, Message message) ;
	// Return a error code (0000 – No error)
	public String sendMessage (MessageType messageType, Message message, TimeToLeave ttl) ;
	// Return a error code (0000 – No error)
	public String sendMessage (MessageType messageType, Message message, Priority priorty) ;
	// Return a error code (0000 – No error)
	public String sendMessage (MessageType messageType, Message message, TimeToLeave ttl, Priority priorty) ;


}
