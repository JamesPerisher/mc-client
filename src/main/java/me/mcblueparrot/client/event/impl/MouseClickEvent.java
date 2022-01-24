package me.mcblueparrot.client.event.impl;




public class MouseClickEvent {

	public final int button;
	public boolean cancelled;
	
	public MouseClickEvent(int button2, boolean cancelled2) {
		this.button = button2;
		this.cancelled = cancelled2;
	}

	public MouseClickEvent(int eventButton) {
		this(eventButton, false);
	}

}
