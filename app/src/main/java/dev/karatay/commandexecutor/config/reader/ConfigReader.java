package dev.karatay.commandexecutor.config.reader;

import dev.karatay.commandexecutor.config.model.Config;
import dev.karatay.commandexecutor.error.CommandErrors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConfigReader {
    private final ConfigReadStrategy readStrategy;

	public Config read() {
    	return readStrategy.read()
			.map(Config::init)
			.orElseThrow(() -> 
					CommandErrors.error(                
						"Failed to load configuration. Please provide a valid configuration via:\n" +
                		"1. Command line argument: -configFile=/path/to/config.json\n" +
                		"2. Inline JSON: -config='{\"conf\":{...}}'\n" +
                		"3. Default config file: config.json in the current directory"
					)
			);    
	}
	
}
