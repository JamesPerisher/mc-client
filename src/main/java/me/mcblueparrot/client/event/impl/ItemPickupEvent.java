package me.mcblueparrot.client.event.impl;


import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;


public class ItemPickupEvent {

	public ItemPickupEvent(EntityPlayer entityPlayer, EntityItem entity) {
		this.player = entityPlayer;
		this.pickedUp = entity;
	}
	public EntityPlayer player;
	public EntityItem pickedUp;

}
