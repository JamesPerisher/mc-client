package me.mcblueparrot.client.event.impl;


import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.ScoreObjective;


public class ScoreboardRenderEvent {

	public final ScoreObjective objective;
	public final ScaledResolution scaledRes;
	public boolean cancelled;
	
	public ScoreboardRenderEvent(ScoreObjective objective2, ScaledResolution scaledRes2, boolean cancelled2) {
		this.objective = objective2;
		this.scaledRes = scaledRes2;
		this.cancelled = cancelled2;
	}

	public ScoreboardRenderEvent(ScoreObjective objective2, ScaledResolution scaledRes2) {
		this(objective2, scaledRes2, false);
	}

}
