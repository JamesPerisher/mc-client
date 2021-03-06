package me.mcblueparrot.client.ui.element;

import net.minecraft.client.Minecraft;
import me.mcblueparrot.client.util.access.AccessGuiChat;

public interface ChatButton {

	int getPriority();
	
	default int getWidth() {
		return Minecraft.getMinecraft().fontRendererObj.getStringWidth(getText()) + 4;
	}

	int getPopupWidth();

	int getPopupHeight();

	String getText();

	void render(int x, int y, boolean mouseDown, boolean wasMouseDown, boolean wasMouseClicked, int mouseX, int mouseY);

	default boolean isOpen() {
		AccessGuiChat chat = AccessGuiChat.getInstance();
		return chat != null && chat.getSelectedChatButton() == this;
	}

}
