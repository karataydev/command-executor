package dev.karatay.commandexecutor.config.reader;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigReaderFactory {
	private static final String CONFIG_ARG_PREFIX = "-config=";
    private static final String CONFIG_FILE_ARG_PREFIX = "-configFile=";
    private static final String DEFAULT_CONFIG_PATH = "~/.config/command-executor/config.json";



	public static ConfigReader getReader(List<String> systemArgs) {
		for(var arg : systemArgs) {
			if(arg.startsWith(CONFIG_ARG_PREFIX)) {
				log.debug("reading config from {} parameter.", CONFIG_ARG_PREFIX);
				String jsonConfig = arg.substring(CONFIG_ARG_PREFIX.length());
                return new ConfigReader(new StringReadStrategy(jsonConfig));
			} else if(arg.startsWith(CONFIG_FILE_ARG_PREFIX)) {
				log.debug("reading config from {} parameter.", CONFIG_ARG_PREFIX);
                String configPath = arg.substring(CONFIG_FILE_ARG_PREFIX.length());
				return new ConfigReader(new FileReadStrategy(configPath));
			}
		}
		
		log.debug("reading config from default path. path: {}", DEFAULT_CONFIG_PATH);
		return new ConfigReader(new FileReadStrategy(DEFAULT_CONFIG_PATH));
	}
}
