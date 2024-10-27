package dev.karatay.commandexecutor;


import dev.karatay.commandexecutor.args.parser.ArgParser;
import dev.karatay.commandexecutor.config.reader.ConfigReaderFactory;
import dev.karatay.commandexecutor.core.executor.ExecutorFactory;


public class CommandExecutorApp {

    public static void main(String[] args) {
		var commandArgs = new ArgParser(args)
			.parse();
		var configReader = ConfigReaderFactory.getReader(commandArgs.getSystemArgs());
		var config = configReader.read();
		var command = config.getCommand(commandArgs.getCommand());
		var executor = ExecutorFactory.getExecutor(command);
		executor.execute();

    }

}

