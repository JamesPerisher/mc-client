package me.mcblueparrot.client.mod.impl.replay.fix;

import net.minecraft.client.renderer.RenderGlobal;
import me.mcblueparrot.client.annotation.ForgeCompat;

@Deprecated
@ForgeCompat
public class SCForgeHooksClient {

	public static boolean renderFirstPersonHand(RenderGlobal context, float partialTicks, int renderPass) {
		return false; // Always render hand
	}

}
