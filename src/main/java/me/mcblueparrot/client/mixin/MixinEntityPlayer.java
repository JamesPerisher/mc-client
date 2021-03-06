package me.mcblueparrot.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import me.mcblueparrot.client.Client;
import me.mcblueparrot.client.event.impl.EntityAttackEvent;
import me.mcblueparrot.client.event.impl.PlayerSleepEvent;

@Mixin(EntityPlayer.class)
public class MixinEntityPlayer {
	
	@Inject(method = "attackTargetEntityWithCurrentItem", at = @At("HEAD"))
	public void attackEntity(Entity entity, CallbackInfo callback) {
		if(entity.canAttackWithItem()) {
			Client.INSTANCE.bus.post(new EntityAttackEvent(entity));
		}
	}

	@Inject(method = "trySleep", at = @At("HEAD"))
	public void onSleep(BlockPos pos, CallbackInfoReturnable<EntityPlayer.EnumStatus> callback) {
		Client.INSTANCE.bus.post(new PlayerSleepEvent((EntityPlayer) (Object) this, pos));
	}

}
