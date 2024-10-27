package dev.karatay.commandexecutor;

import java.util.Arrays;

import dev.karatay.commandexecutor.args.parser.ArgParser;
import dev.karatay.commandexecutor.config.reader.ConfigReaderFactory;
import dev.karatay.commandexecutor.core.executor.ExecutorFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandExecutorApp {

    public static void main(String[] args) {
		log.debug("Executor started with args: {}", Arrays.asList(args));
		var commandArgs = new ArgParser(args)
			.parse();
		var configReader = ConfigReaderFactory.getReader(commandArgs.getSystemArgs());
		var config = configReader.read();
		var command = config.getCommand(commandArgs.getCommand());
		var executor = ExecutorFactory.getExecutor(command);
		executor.execute();

    }

}

