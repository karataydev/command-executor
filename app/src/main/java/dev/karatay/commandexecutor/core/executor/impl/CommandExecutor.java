package dev.karatay.commandexecutor.core.executor.impl;

import dev.karatay.commandexecutor.config.model.CommandElement;
import dev.karatay.commandexecutor.core.executor.Executor;
import dev.karatay.commandexecutor.error.CommandErrors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class CommandExecutor implements Executor {
	private CommandElement commandElement;

	@Override
	public void execute() {
		commandElement.validate();
		var pb = new ProcessBuilder(commandElement.getValue());
		try {
			pb.start();
		} catch(Exception ex) {
			throw CommandErrors.commandRunError(commandElement, ex);
		}	
	}
	
}
