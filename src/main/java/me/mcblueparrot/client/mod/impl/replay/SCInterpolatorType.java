package me.mcblueparrot.client.mod.impl.replay;




public enum SCInterpolatorType {
	CATMULL("Catmull-Rom Spline"),
	CUBIC("Cubic Spline"),
	LINEAR("Linear");

	private String name;

	SCInterpolatorType(String string) {
		this.name = string;
	}

	@Override
	public String toString() {
		return name;
	}
}