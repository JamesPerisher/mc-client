package me.mcblueparrot.client.event.impl;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;


public class PlayerSleepEvent {

	public PlayerSleepEvent(EntityPlayer entityPlayer2, BlockPos pos2) {
		this.entityPlayer = entityPlayer2;
		this.pos = pos2;
	}
	public EntityPlayer entityPlayer;
	public BlockPos pos;

}
