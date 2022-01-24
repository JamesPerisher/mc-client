package me.mcblueparrot.client.event.impl;

import me.mcblueparrot.client.annotation.ForgeCompat;


public class PreGameOverlayRenderEvent {

	public final float partialTicks;
	public final GameOverlayElement type;
	public boolean cancelled;

	@Deprecated
	@ForgeCompat
	public void setCanceled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public PreGameOverlayRenderEvent(float partialTicks2, GameOverlayElement type2, boolean cancelled2) {
		this.partialTicks = partialTicks2;
		this.type = type2;
		this.cancelled = cancelled2;
	}

	public PreGameOverlayRenderEvent(float partialTicks2, GameOverlayElement all) {
		this(partialTicks2, all, false);
	}

}
