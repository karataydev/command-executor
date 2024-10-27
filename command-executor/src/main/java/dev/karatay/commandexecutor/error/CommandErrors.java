package dev.karatay.commandexecutor.error;

import java.text.MessageFormat;

import dev.karatay.commandexecutor.config.model.CommandElement;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommandErrors {
	
	public CommandException commandValueEmptyError(CommandElement configElem) {
		return error(MessageFormat.format("Command value cannot be empty. Check your config. CommandName: {0}", configElem.getCommandName()));  
	}

	public CommandException execTypeEmptyError(CommandElement configElem) {
		return error(MessageFormat.format("Command Exec type cannot be empty. Check your config. CommandName: {0}", configElem.getCommandName()));  
	}

	public CommandException linkOpenError(CommandElement configElem, Exception ex) {
		return error(MessageFormat.format("Could not open provided link. CommandName: {0}, LinkValue: {1}", 
					configElem.getCommandName(), configElem.getValue()), 
				ex);  
	}

	public CommandException commandRunError(CommandElement configElem, Exception ex) {
		return error(MessageFormat.format("Could not run provided command. CommandName: {0}, CommandValue: {1}", 
					configElem.getCommandName(), configElem.getValue()), 
				ex);  
	}

	public CommandException exitCodeNotZeroError(CommandElement configElem, int exitCode) {
		return error(MessageFormat.format("Command returned exit code {3}. CommandName: {0}, CommandValue: {1}", 
					configElem.getCommandName(), configElem.getValue(), exitCode));  
	}

	public CommandException commandNotFound(String commandName) {
		return error(MessageFormat.format("Command not found. Check your config. CommandName: {0}", commandName));  
	}



	public CommandException error(String errorMessage) {
		return new CommandException(errorMessage);  
	}

	public CommandException error(String errorMessage, Exception ex) {
		return new CommandException(errorMessage, ex);
	}
	
}
