package me.mcblueparrot.client.event.impl;




public class PlayerHeadRotateEvent {

	public final float yaw;
	public final float pitch;
	public boolean cancelled;
	
	public PlayerHeadRotateEvent(float yaw2, float pitch2, boolean cancelled2) {
		this.yaw = yaw2;
		this.pitch = pitch2;
		this.cancelled = cancelled2;
	}

	public PlayerHeadRotateEvent(float yaw2, float pitch2) {
		this(yaw2, pitch2, false);
	}

}
