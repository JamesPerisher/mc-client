package me.mcblueparrot.client.mixin;


import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.tileentity.TileEntity;
import me.mcblueparrot.client.culling.Cullable;

@Mixin(TileEntity.class)
public abstract class MixinTileEntity implements Cullable {

	public boolean culled = false;
	
	public boolean getCulled() {
		return this.culled;
	}
	
	public boolean isCulled() { // some duckers inconsistent use of isCulled/getCulled wasted 30mins fuck you
		return this.getCulled();
	}
	
	public void setCulled(Boolean value) {
		this.culled = value;
	}


}
