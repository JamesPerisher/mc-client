package me.mcblueparrot.client.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.tileentity.TileEntity;
import me.mcblueparrot.client.culling.Cullable;

@Mixin(TileEntity.class)
public abstract class MixinTileEntity implements Cullable {

	
	
	private boolean culled = false;
	
	public boolean getCulled() {
		return this.culled;
	}
	
	public void setCulled(Boolean value) {
		this.culled = value;
	}


}
