package dev.karatay.commandexecutor.args.parser;

import dev.karatay.commandexecutor.args.model.CommandArgs;
import dev.karatay.commandexecutor.error.CommandErrors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ArgParser {
	// args from stdin
	private final String[] args;

	public CommandArgs parse() {
		var commandArgs = new CommandArgs();
		if (args == null || args.length < 1) {
			throw CommandErrors.error("At least one arg(command) expected");
		}
		for(var arg : args) {
			if(commandArgs.getCommand() == null && arg.startsWith("-")) {
				commandArgs.getSystemArgs().add(arg);
			} else if (commandArgs.getCommand() == null) {
				commandArgs.setCommand(arg);
			} else {
				commandArgs.getUserArgs().add(arg);
			}
		}
		log.debug("parsed CommandArgs: {}", commandArgs);
		if (commandArgs.getCommand() == null) {
			throw CommandErrors.error("Command not found in the args.");
		}
		return commandArgs;
	}

	
}
