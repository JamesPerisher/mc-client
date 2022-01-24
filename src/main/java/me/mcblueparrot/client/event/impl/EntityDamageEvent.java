package me.mcblueparrot.client.event.impl;


import net.minecraft.entity.Entity;


public class EntityDamageEvent {

	public EntityDamageEvent(Entity entity2) {
		this.entity = entity2;
	}

	public Entity entity;

}
