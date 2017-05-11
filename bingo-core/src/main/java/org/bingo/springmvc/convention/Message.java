package org.bingo.springmvc.convention;

public class Message {
	
	public static final String SUCCESS="success";
	
	public static final String ERROR="error";
	
	public Message(String message) {
		super();
		this.message = message;
	}
	
	public Message(String type, String message) {
		super();
		this.type = type;
		this.message = message;
	}

	private String type;
	
	private String message;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
