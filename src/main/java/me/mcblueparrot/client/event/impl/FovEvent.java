package me.mcblueparrot.client.event.impl;




public class FovEvent {

	public FovEvent(Float returnValue, float partialTicks2) {
		this.fov = returnValue;
		this.partialTicks = partialTicks2;
	}
	public float fov;
	public float partialTicks;

}
