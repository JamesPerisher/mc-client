package me.mcblueparrot.client.event.impl;




public class SoundPlayEvent {

	public String soundName;

	public float volume;

	public float pitch;

	public float originalVolume;

	public float originalPitch;

	public SoundPlayEvent(String soundName2, float volume2, float pitch2, float originalVolume2, float originalPitch2) {
		this.soundName = soundName2;
		this.volume = volume2;
		this.pitch = pitch2;
		this.originalVolume = originalVolume2;
		this.originalPitch = originalPitch2;
	}

	/**
	 * Sets volume to <code>0</code>.
	 */
	public void cancel() {
		volume = 0;
	}

}
