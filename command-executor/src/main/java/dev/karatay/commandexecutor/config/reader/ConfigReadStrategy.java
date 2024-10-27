package dev.karatay.commandexecutor.config.reader;

import java.util.Optional;

import dev.karatay.commandexecutor.config.model.Config;

public interface ConfigReadStrategy {
	Optional<Config> read();
}
