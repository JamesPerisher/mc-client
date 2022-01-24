package me.mcblueparrot.client.event.impl;




public class SendChatMessageEvent {

	public final String message;
	public boolean cancelled;
	
	public SendChatMessageEvent(String message2, boolean cancelled2) {
		this.message = message2;
		this.cancelled = cancelled2;
	}

	public SendChatMessageEvent(String message2) {
		this(message2, false);
	}

}
