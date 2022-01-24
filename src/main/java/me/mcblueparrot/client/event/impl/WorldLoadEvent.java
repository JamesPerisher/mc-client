package me.mcblueparrot.client.event.impl;


import net.minecraft.client.multiplayer.WorldClient;


public class WorldLoadEvent {

	public WorldLoadEvent(WorldClient world2) {
		this.world = world2;
	}

	public WorldClient world;

}
