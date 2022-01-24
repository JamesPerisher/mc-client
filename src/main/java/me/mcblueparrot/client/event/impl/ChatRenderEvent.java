package me.mcblueparrot.client.event.impl;


import net.minecraft.client.gui.GuiNewChat;


public class ChatRenderEvent {

	public GuiNewChat chat;
	public final int updateCounter;
	public final float partialTicks;
	public boolean cancelled;
	
	public ChatRenderEvent(GuiNewChat chat2, int updateCounter2, float partialTicks2, boolean cancelled2) {
		this.chat = chat2;
		this.updateCounter = updateCounter2;
		this.partialTicks = partialTicks2;
		this.cancelled = cancelled2;
	}

	public ChatRenderEvent(GuiNewChat guiNewChat, int updateCounter2, float renderPartialTicks) {
		this(guiNewChat, updateCounter2, renderPartialTicks, false);
	}

}
