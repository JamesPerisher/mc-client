package me.mcblueparrot.client.event.impl;

import java.util.Collection;


import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;


public class PostGuiInitEvent {

	public GuiScreen screen;
	public final Collection<GuiButton> buttonList;
	
	public PostGuiInitEvent(GuiScreen screen2, Collection<GuiButton> buttonList2) {
		this.screen = screen2;
		this.buttonList = buttonList2;
	}

}
