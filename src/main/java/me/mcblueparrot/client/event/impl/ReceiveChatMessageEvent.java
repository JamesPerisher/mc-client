package me.mcblueparrot.client.event.impl;




public class ReceiveChatMessageEvent {

	public final boolean actionBar;
	public final String message;
	/**
	 * Whether the event is fired from the replay mod.
	 */
	public final boolean replay;
	public boolean cancelled;
	
	public ReceiveChatMessageEvent(boolean actionBar2, String message2, boolean replay2, boolean cancelled2) {
		this.actionBar = actionBar2;
		this.message = message2;
		this.replay = replay2;
		this.cancelled = cancelled2;
	}

	public ReceiveChatMessageEvent(boolean b, String textWithoutFormattingCodes, boolean c) {
		this(b, textWithoutFormattingCodes, c, false);
	}

}
