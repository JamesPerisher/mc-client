package me.mcblueparrot.client.event.impl;


import net.minecraft.entity.Entity;


public class EntityAttackEvent {

	public EntityAttackEvent(Entity entity) {
		this.victim = entity;
	}

	public Entity victim;

}
