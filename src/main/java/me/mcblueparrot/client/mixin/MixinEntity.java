package me.mcblueparrot.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.entity.Entity;
import me.mcblueparrot.client.culling.Cullable;
import me.mcblueparrot.client.util.access.AccessEntity;



@Mixin(Entity.class)
public abstract class MixinEntity implements Cullable, AccessEntity {
	@Accessor
	public abstract boolean getIsInWeb();
	
	public boolean culled = false;
	
	public boolean isCulled() {
		return this.culled;
	}

	public void setCulled(boolean culled2) {
		this.culled = culled2;
	}

}
