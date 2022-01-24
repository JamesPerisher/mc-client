package me.mcblueparrot.client.event.impl;


import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityItem;


public class ItemEntityRenderEvent {
	
	public boolean cancelled;
	public final EntityItem entity;
	public final double x;
	public final double y;
	public final double z;
	public final float partialTicks;
	public final IBakedModel model;
	public int result = -1;
	
	public ItemEntityRenderEvent(boolean cancelled2, EntityItem entity2, double x2, double y2, double z2, float partialTicks2, IBakedModel model2) {
		this.cancelled = cancelled2;
		this.entity = entity2;
		this.x = x2;
		this.y = y2;
		this.z = z2;
		this.partialTicks = partialTicks2;
		this.model = model2;
	}

	public ItemEntityRenderEvent(EntityItem itemIn, double x2, double y2, double z2, float partialTicks2,
			IBakedModel model2) {
		this(false, itemIn, x2, y2, z2, partialTicks2, model2);
	}

}
