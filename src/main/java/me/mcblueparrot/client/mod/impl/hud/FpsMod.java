package me.mcblueparrot.client.mod.impl.hud;

import net.minecraft.client.Minecraft;
import me.mcblueparrot.client.mod.hud.SmoothCounterHudMod;

public class FpsMod extends SmoothCounterHudMod {

	public FpsMod() {
		super("FPS", "fps", "Display the FPS (frames per second).");
	}

	@Override
	public int getIntValue() {
		return Minecraft.getDebugFPS();
	}

	@Override
	public String getSuffix() {
		return "FPS";
	}



}
