package me.mcblueparrot.client.mod.impl;

import com.google.gson.annotations.Expose;

import net.minecraft.client.Minecraft;
import me.mcblueparrot.client.mod.ConfigOnlyMod;
import me.mcblueparrot.client.mod.ModCategory;
import me.mcblueparrot.client.mod.annotation.ConfigOption;
import me.mcblueparrot.client.ui.screen.mods.ModsScreen;
import me.mcblueparrot.client.util.data.Colour;
import me.mcblueparrot.client.util.font.Font;
import me.mcblueparrot.client.util.font.SlickFontRenderer;

public class SolClientMod extends ConfigOnlyMod {

	public static SolClientMod instance;

	@Expose
	@ConfigOption("UI Colour")
	public Colour uiColour = new Colour(255, 180, 0);
	public Colour uiHover;
	public Colour uiActive = new Colour(255, 0, 0, 150);

	@Expose
	@ConfigOption("Fancy Font")
	public boolean fancyFont = true;

	@Expose
	@ConfigOption("Show Logo in Inventory")
	public boolean logoInInventory;

	public SolClientMod() {
		super("Sol Client", "sol_client", "Settings for Sol Client, a simple and easy to use Minecraft client.", ModCategory.NONE);
		instance = this;
	}

	@Override
	public void onRegister() {
		super.onRegister();
		uiHover = getUiHover();
	}

	public static Font getFont() {
		if(instance.fancyFont) {
			return SlickFontRenderer.DEFAULT;
		}
		else {
			return (Font) Minecraft.getMinecraft().fontRendererObj;
		}
	}

	@Override
	public void postOptionChange(String key, Object value) {
		super.postOptionChange(key, value);

		if(key.equals("fancyFont") && mc.currentScreen instanceof ModsScreen) {
			ModsScreen screen = (ModsScreen) mc.currentScreen;
			screen.updateFont();
		}

		if(key.equals("uiColour")) {
			uiHover = getUiHover();
		}
	}

	@Override
	public String getDescription() {
		if(!instance.fancyFont) {
			return "Settings for Sol Client, an easy to use Minecraft client.";
		}
		return super.getDescription();
	}

	private Colour getUiHover() {
		return uiColour.add(60);
	}

}
