package dev.karatay.commandexecutor.error;

import lombok.Getter;

@Getter
public class CommandException extends RuntimeException {
	private Exception cause;
	
	public CommandException() {}

	public CommandException(String msg) {
		super(msg);
	}

	public CommandException(String msg, Exception cause) {
		super(msg);
		this.cause = cause;
	}


}
