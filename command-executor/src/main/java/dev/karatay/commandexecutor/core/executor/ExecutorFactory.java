package dev.karatay.commandexecutor.core.executor;

import java.text.MessageFormat;

import dev.karatay.commandexecutor.config.model.CommandElement;
import dev.karatay.commandexecutor.core.executor.impl.CommandExecutor;
import dev.karatay.commandexecutor.core.executor.impl.LinkExecutor;
import dev.karatay.commandexecutor.domain.model.ExecType;
import dev.karatay.commandexecutor.error.CommandErrors;

public class ExecutorFactory {

	public static Executor getExecutor(CommandElement commandElement) {
		commandElement.validate();
		var execType = commandElement.getExecType();
		if(execType == ExecType.Cmd) {
			return new CommandExecutor(commandElement);
		} else if (execType == ExecType.Link) {
			return new LinkExecutor(commandElement);
		}

		throw CommandErrors.error(MessageFormat.format("Unknown execType! execType: {0}", execType));
	}
}
