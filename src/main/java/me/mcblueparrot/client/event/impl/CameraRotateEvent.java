package me.mcblueparrot.client.event.impl;




public class CameraRotateEvent {

	public CameraRotateEvent(float rotationYaw, float rotationPitch, float rotationRoll) {
		this.yaw = rotationYaw;
		this.pitch = rotationPitch;
		this.roll = rotationRoll;
	}
	public float yaw;
	public float pitch;
	public float roll;

}
