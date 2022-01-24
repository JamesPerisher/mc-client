package me.mcblueparrot.client.event.impl;


import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;


public class RenderChunkPositionEvent {

	public RenderChunkPositionEvent(RenderChunk renderChunk, BlockPos pos) {
		this.chunk = renderChunk;
		this.position = pos;
	}
	public RenderChunk chunk;
	public BlockPos position;

}
