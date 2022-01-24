package me.mcblueparrot.client.event.impl;

import net.minecraft.client.gui.GuiScreen;


public class PreGuiInitEvent {

	public final GuiScreen screen;
	public boolean cancelled;
	
	public PreGuiInitEvent(GuiScreen screen2, boolean cancelled2) {
		this.screen = screen2;
		this.cancelled = cancelled2;
	}

	public PreGuiInitEvent(GuiScreen instance) {
		this(instance, false);
	}

}
