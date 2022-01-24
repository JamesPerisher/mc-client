package me.mcblueparrot.client.event.impl;


import net.minecraft.client.multiplayer.ServerData;
import me.mcblueparrot.client.DetectedServer;


public class ServerConnectEvent {

	public ServerConnectEvent(ServerData data2, DetectedServer detectedServer) {
		this.data = data2;
		this.server = detectedServer;
	}
	public ServerData data;
	public DetectedServer server;

}
