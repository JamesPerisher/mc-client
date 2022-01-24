package me.mcblueparrot.client.event.impl;




public class PostGuiRenderEvent {

	public PostGuiRenderEvent(float partialTicks2) {
		this.partialTicks = partialTicks2;
	}

	public float partialTicks;

}
