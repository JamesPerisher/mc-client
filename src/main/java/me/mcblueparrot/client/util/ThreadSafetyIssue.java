package me.mcblueparrot.client.util;

public class ThreadSafetyIssue extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ThreadSafetyIssue(String message) {
		super(message);
	}

}
