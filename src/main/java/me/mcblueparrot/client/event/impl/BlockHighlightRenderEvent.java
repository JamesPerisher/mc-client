package me.mcblueparrot.client.event.impl;


import net.minecraft.util.MovingObjectPosition;


public class BlockHighlightRenderEvent {

	public MovingObjectPosition movingObjectPosition;
	public final float partialTicks;
	public boolean cancelled;
	
	public BlockHighlightRenderEvent(MovingObjectPosition movingObjectPosition2, float partialTicks2, boolean cancelled2) {
		this.movingObjectPosition = movingObjectPosition2;
		this.partialTicks = partialTicks2;
		this.cancelled = cancelled2;
	}

	public BlockHighlightRenderEvent(MovingObjectPosition objectMouseOver, float renderPartialTicks) {
		this(objectMouseOver, renderPartialTicks, false);
	}

}
