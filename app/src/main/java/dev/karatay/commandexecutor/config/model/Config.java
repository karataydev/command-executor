package dev.karatay.commandexecutor.config.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import dev.karatay.commandexecutor.error.CommandErrors;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Config {
	private Map<String, CommandElement> conf = new HashMap<>();


	public Config init() {
		for (var entry: conf.entrySet()) {
			entry.getValue().setCommandName(entry.getKey());
		}
		return this;
	}

	public CommandElement getCommand(String commandName) {
		return getCommandByName(commandName)
			.orElseThrow(() -> CommandErrors.commandNotFound(commandName));
	}


	public Optional<CommandElement> getCommandByName(String name) {
        return Optional.ofNullable(conf.get(name));
    }
}
