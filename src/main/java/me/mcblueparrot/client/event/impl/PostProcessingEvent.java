package me.mcblueparrot.client.event.impl;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.shader.ShaderGroup;


public class PostProcessingEvent {

	public Type type;
	public List<ShaderGroup> groups = new ArrayList<>();

	public enum Type {
		RENDER,
		UPDATE
	}
	
	public PostProcessingEvent(Type type2) {
		this.type = type2;
	}

}
