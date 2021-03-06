package me.mcblueparrot.client.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.renderer.ChunkRenderContainer;
import net.minecraft.client.renderer.chunk.RenderChunk;
import me.mcblueparrot.client.Client;
import me.mcblueparrot.client.event.impl.PreRenderChunkEvent;

@Mixin(ChunkRenderContainer.class)
public class MixinChunkRenderContainer {

	@Inject(method = "preRenderChunk", at = @At("RETURN"))
	public void preRenderChunk(RenderChunk renderChunkIn, CallbackInfo callback) {
		Client.INSTANCE.bus.post(new PreRenderChunkEvent(renderChunkIn));
	}

}
