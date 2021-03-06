package me.mcblueparrot.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.storage.WorldInfo;
import me.mcblueparrot.client.Client;
import me.mcblueparrot.client.event.impl.TimeEvent;

@Mixin(WorldInfo.class)
public class MixinWorldInfo {

	@Inject(method = "getWorldTime", at = @At("HEAD"), cancellable = true)
	public void overrideWorldTime(CallbackInfoReturnable<Long> callback) {
		callback.setReturnValue(Client.INSTANCE.bus.post(new TimeEvent(worldTime)).time);
	}

	@Shadow
	private long worldTime;

}
