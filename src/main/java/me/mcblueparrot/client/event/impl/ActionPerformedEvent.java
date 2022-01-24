package me.mcblueparrot.client.event.impl;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;


public class ActionPerformedEvent {

	public final GuiScreen gui;
	public final GuiButton button;
	public boolean cancelled;
	
	
	public ActionPerformedEvent(GuiScreen gui2, GuiButton button2, boolean cancelled2) {
		this.gui = gui2;
		this.button = button2;
		this.cancelled = cancelled2;
	}


	public ActionPerformedEvent(GuiScreen guiScreen, GuiButton instance) {
		this(guiScreen, instance, false);
	}

}
