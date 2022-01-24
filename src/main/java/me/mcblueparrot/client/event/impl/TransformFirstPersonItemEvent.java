package me.mcblueparrot.client.event.impl;


import net.minecraft.item.ItemStack;


public class TransformFirstPersonItemEvent {

	public TransformFirstPersonItemEvent(ItemStack itemToRender2, float equipProgress2, float swingProgress2) {
		this.itemToRender = itemToRender2;
		this.equipProgress = equipProgress2;
		this.swingProgress = swingProgress2;
	}
	public ItemStack itemToRender;
	public float equipProgress;
	public float swingProgress;

}
