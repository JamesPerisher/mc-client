package me.mcblueparrot.client.event.impl;


import net.minecraft.client.gui.GuiScreen;


public class OpenGuiEvent {

	public OpenGuiEvent(GuiScreen guiScreenIn) {
		this.screen = guiScreenIn;
	}

	public GuiScreen screen;

}
