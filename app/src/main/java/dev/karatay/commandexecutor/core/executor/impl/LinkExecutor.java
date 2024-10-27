package dev.karatay.commandexecutor.core.executor.impl;

import java.net.URI;

import dev.karatay.commandexecutor.config.model.CommandElement;
import dev.karatay.commandexecutor.core.executor.Executor;
import dev.karatay.commandexecutor.error.CommandErrors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LinkExecutor implements Executor {
	private CommandElement commandElement;

	@Override
	public void execute() {
		commandElement.validate();
		var desktop = java.awt.Desktop.getDesktop();
		try {
			URI oURL = new URI(commandElement.getValue());
			desktop.browse(oURL);
		} catch (Exception ex) {
			throw CommandErrors.linkOpenError(commandElement, ex);
		}
	}
}
