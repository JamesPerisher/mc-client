package me.mcblueparrot.client.mod.impl.replay;




public enum SCCameraType {
	CLASSIC("Classic"),
	VANILLA_ISH("Vanilla-ish");

	private String name;

	SCCameraType(String string) {
		this.name = string;
	}

	@Override
	public String toString() {
		return name;
	}
}