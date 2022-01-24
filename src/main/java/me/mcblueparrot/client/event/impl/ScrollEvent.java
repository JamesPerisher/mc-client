package me.mcblueparrot.client.event.impl;




public class ScrollEvent {

	public final int amount;
	public boolean cancelled;
	
	
	public ScrollEvent(int amount2, boolean cancelled2) {
		this.amount = amount2;
		this.cancelled = cancelled2;
	}


	public ScrollEvent(int dWheel) {
		this(dWheel, false);
	}

}
