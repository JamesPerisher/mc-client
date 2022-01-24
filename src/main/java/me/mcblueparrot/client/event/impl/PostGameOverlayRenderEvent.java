package me.mcblueparrot.client.event.impl;




public class PostGameOverlayRenderEvent {

	public PostGameOverlayRenderEvent(float partialTicks2, GameOverlayElement all) {
		this.partialTicks = partialTicks2;
		this.type = all;
	}
	public float partialTicks;
	public GameOverlayElement type;

}
