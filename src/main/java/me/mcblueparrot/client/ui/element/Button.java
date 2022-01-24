package me.mcblueparrot.client.ui.element;


import me.mcblueparrot.client.util.data.Colour;
import me.mcblueparrot.client.util.data.Rectangle;
import me.mcblueparrot.client.util.font.Font;
import me.mcblueparrot.client.util.font.SlickFontRenderer;


public class Button {

	private Font font;
	private String text;
	private Rectangle bounds;
	private Colour colour;
	private Colour hoverColour;

	public Button(Font font2, String string, Rectangle rectangle, Colour colour2, Colour colour3) {
		this.font = font2;
		this.text = string;
		this.bounds = rectangle;
		this.colour = colour2;
		this.hoverColour = colour3;
	}

	public void render(int mouseX, int mouseY) {
		Colour buttonColour = contains(mouseX, mouseY) ? hoverColour : colour;
//		if(bounds.getWidth() == 100 && bounds.getHeight() == 20) {
//			// Use rounded button.
//			GlStateManager.enableBlend();
//
//			Utils.glColour(buttonColour);
//			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gui/sol_client_" +
//					"button_" + Utils.getTextureScale() + ".png"));
//			Gui.drawModalRectWithCustomSizedTexture(bounds.getX(), bounds.getY(), 0, 0, 100, 20, 100, 20);
//		}
//		else {
			// Fall back to square button.
			bounds.fill(Colour.BLACK_128);
			bounds.stroke(buttonColour);
//		}

		font.renderString(text, bounds.getX() + (bounds.getWidth() / 2) - (font.getWidth(text) / 2),
				bounds.getY() + (bounds.getHeight() / 2) + (font instanceof SlickFontRenderer ? 0 : 1) - 5, -1);
	}

	public boolean contains(int x, int y) {
		return bounds.contains(x, y);
	}

}
