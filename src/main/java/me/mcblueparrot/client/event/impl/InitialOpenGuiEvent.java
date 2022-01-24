package me.mcblueparrot.client.event.impl;


import net.minecraft.client.gui.GuiScreen;


public class InitialOpenGuiEvent {

	public InitialOpenGuiEvent(GuiScreen guiScreenIn) {
		this.screen = guiScreenIn;
	}

	public GuiScreen screen;

}
