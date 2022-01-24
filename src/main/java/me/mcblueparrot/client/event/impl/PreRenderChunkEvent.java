package me.mcblueparrot.client.event.impl;


import net.minecraft.client.renderer.chunk.RenderChunk;


public class PreRenderChunkEvent {

	public PreRenderChunkEvent(RenderChunk renderChunkIn) {
		this.chunk = renderChunkIn;
	}

	public RenderChunk chunk;

}
