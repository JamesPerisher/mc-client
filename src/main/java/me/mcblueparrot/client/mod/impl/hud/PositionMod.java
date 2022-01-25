package me.mcblueparrot.client.mod.impl.hud;

import com.google.gson.annotations.Expose;

import net.minecraft.util.MathHelper;
import me.mcblueparrot.client.mod.annotation.ConfigOption;
import me.mcblueparrot.client.mod.hud.HudMod;
import me.mcblueparrot.client.util.data.Colour;
import me.mcblueparrot.client.util.data.Position;
import me.mcblueparrot.client.util.data.Rectangle;

public class PositionMod extends HudMod {

	@Expose
	@ConfigOption("Background")
	private boolean background = true;
	@Expose
	@ConfigOption("Background Colour")
	private Colour backgroundColour = new Colour(0, 0, 0, 100);
	@Expose
	@ConfigOption("Border")
	private boolean border = false;
	@Expose
	@ConfigOption("Border Colour")
	private Colour borderColour = Colour.BLACK;
	@Expose
	@ConfigOption("Axis Label Colour")
	private Colour axisLabelColour = new Colour(0, 150, 255);
	@Expose
	@ConfigOption("Value Colour")
	private Colour axisValueColour = Colour.WHITE;
	@Expose
	@ConfigOption("Cardinal Direction")
	private boolean cardinalDirection = true;
	@Expose
	@ConfigOption("Cardinal Direction Colour")
	private Colour cardinalDirectionColour = new Colour(0, 150, 255);
	@Expose
	@ConfigOption("Axis Direction")
	private boolean axisDirection = true;
	@Expose
	@ConfigOption("Axis Direction Colour")
	private Colour axisDirectionColour = new Colour(0, 150, 255);
	@Expose
	@ConfigOption("Text Shadow")
	private boolean shadow = true;
	
	private int minwidth = 130;
	private int width=0;

	public PositionMod() {
		super("Coordinates", "coordinates", "Display your coordinates.");
	}
	
	@Override
	public Rectangle getBounds(Position position) {
		return new Rectangle(position.getX(), position.getY(), width+8, 4 + 3*(font.FONT_HEIGHT + 2));
	}

	private int max(int a, int b) {
		if(a>b) {
			return a;
		} else {
			return b;
		}
	}

	@Override
	public void render(Position position, boolean editMode) {
		int subtract = 0;
		if(shadow) {
			subtract++;
		}

		if(background) {
			getBounds(position).fill(backgroundColour);
		}

		if(border) {
			getBounds(position).stroke(borderColour);
		}

		double x, y, z, yaw;
		if(editMode) {
			x = 0;
			y = 0;
			z = 0;
			yaw = -90;
		}
		else {
			x = mc.thePlayer.posX;
			y = mc.thePlayer.posY;
			z = mc.thePlayer.posZ;
			yaw = mc.thePlayer.rotationYaw;
		}
		int cardinalDirectionIndex = MathHelper.floor_double(
				((MathHelper.wrapAngleTo180_double(yaw) + 180D + 22.5D) % 360D) / 45D);
		String[] cardinalDirections = {
				"N  ",
				"NE",
				"E  ",
				"SE",
				"S  ",
				"SW",
				"W  ",
				"NW"
		};
		String xDirection = null;
		String zDirection = null;
		switch(cardinalDirectionIndex) {
			case 0:
				zDirection = "-";
				break;
			case 1:
				zDirection = "-";
				xDirection = "+";
				break;
			case 2:
				xDirection = "+";
				break;
			case 3:
				zDirection = "+";
				xDirection = "+";
				break;
			case 4:
				zDirection = "+";
				break;
			case 5:
				zDirection = "+";
				xDirection = "-";
				break;
			case 6:
				xDirection = "-";
				break;
			case 7:
				zDirection = "-";
				xDirection = "-";
				break;
		}
		
		

		float textx;
		float rendery;
		width = minwidth;
		
		// Drawing position universal
		rendery = position.getY() + 4;
		textx = font.drawString("X: ",
				position.getX() + 4,
				rendery            , axisDirectionColour.getValue(), shadow);
		
		textx = font.drawString(Integer.toString((int) x),
				textx - subtract,
				rendery         , axisValueColour.getValue(), shadow);
		
		textx = font.drawString(", Y: ",
				textx           ,
				rendery         , axisDirectionColour.getValue(), shadow);
		
		textx = font.drawString(Integer.toString((int) y),
				textx - subtract,
				rendery         , axisValueColour.getValue(), shadow);
		
		textx = font.drawString(", Z: ",
				textx           ,
				rendery         , axisDirectionColour.getValue(), shadow);
		
		textx = font.drawString(Integer.toString((int) z),
				textx - subtract,
				rendery         , axisValueColour.getValue(), shadow);
		
		width = max(font.getStringWidth("X: "+Integer.toString((int) x)+", Y: "+Integer.toString((int) y)+", Z: "+Integer.toString((int) z)), width);
		
		
		switch (mc.thePlayer.dimension) {
		case -1:
			// Drawing position from nether
			rendery += font.FONT_HEIGHT + 2;
			textx = font.drawString("X: ",
					position.getX() + 4,
					rendery            , axisDirectionColour.getValue(), shadow);
			
			textx = font.drawString(Integer.toString((int) x*8),
					textx - subtract,
					rendery         , axisValueColour.getValue(), shadow);
			
			textx = font.drawString(", Y: ",
					textx           ,
					rendery         , axisDirectionColour.getValue(), shadow);
			
			textx = font.drawString(Integer.toString((int) y),
					textx - subtract,
					rendery         , axisValueColour.getValue(), shadow);
			
			textx = font.drawString(", Z: ",
					textx           ,
					rendery         , axisDirectionColour.getValue(), shadow);
			
			textx = font.drawString(Integer.toString((int) z*8),
					textx - subtract,
					rendery         , axisValueColour.getValue(), shadow);
			
			
			width = max(font.getStringWidth("X: "+Integer.toString((int) x*8)+", Y: "+Integer.toString((int) y)+", Z: "+Integer.toString((int) z*8)), width);
			break;
			
		case 0:
			// Drawing position from overworld
			rendery += font.FONT_HEIGHT + 2;
			textx = font.drawString("X: ",
					position.getX() + 4,
					rendery            , axisDirectionColour.getValue(), shadow);
			
			textx = font.drawString(Integer.toString((int) x/8),
					textx - subtract,
					rendery         , axisValueColour.getValue(), shadow);
			
			textx = font.drawString(", Y: ",
					textx           ,
					rendery         , axisDirectionColour.getValue(), shadow);
			
			textx = font.drawString(Integer.toString((int) y),
					textx - subtract,
					rendery         , axisValueColour.getValue(), shadow);
			
			textx = font.drawString(", Z: ",
					textx           ,
					rendery         , axisDirectionColour.getValue(), shadow);
			
			textx = font.drawString(Integer.toString((int) z/8),
					textx - subtract,
					rendery         , axisValueColour.getValue(), shadow);
			
			width = max(font.getStringWidth("X: "+Integer.toString((int) x/8)+", Y: "+Integer.toString((int) y)+", Z: "+Integer.toString((int) z/8)), width);
			break;
		default: // skip rendering scaled coords for other dimensions
			break;
		}
		
		// drawing facing N, NW, W, etc etc
		String facing = cardinalDirections[cardinalDirectionIndex];
		if(cardinalDirection) {
			rendery += font.FONT_HEIGHT + 2;
			
			textx = font.drawString("Facing: ",
					position.getX() + 4,
					rendery         , cardinalDirectionColour.getValue(), shadow);
			
			textx = font.drawString(facing + "    ",
					textx - subtract,
					rendery         , axisValueColour.getValue(), shadow);
			
		}
		
		// Drawing +x-z etc
		if(xDirection != null && axisDirection) {
			textx = font.drawString(xDirection + "x ",
					textx - subtract,
					rendery         , axisDirectionColour.getValue(), shadow);
		}

		if(zDirection != null && axisDirection) {
			textx = font.drawString(zDirection + "z ",
					textx - subtract,
					rendery         , axisDirectionColour.getValue(), shadow);
		}

	}

}
