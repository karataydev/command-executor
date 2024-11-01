package dev.karatay.commandexecutor.config.reader;

import java.io.File;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Matcher;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.karatay.commandexecutor.config.model.CommandElement;
import dev.karatay.commandexecutor.config.model.Config;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class FileReadStrategy implements ConfigReadStrategy {
    
	private String configPath;
	private final ObjectMapper objectMapper;

    public FileReadStrategy(String configPath) {
        this.configPath = configPath;
		this.objectMapper = new ObjectMapper();
    }


	@Override
	public Optional<Config> read() {
		try {
			configPath = configPath.replaceFirst("^~", Matcher.quoteReplacement(System.getProperty("user.home")));
			var configMap = objectMapper.readValue(new File(configPath), new TypeReference<HashMap<String, CommandElement>>(){});
			var config = new Config(configMap);
			return Optional.of(config);
		} catch (Exception e) {
			System.err.print("Error reading configuration file: " + configPath);
			e.printStackTrace();
			return Optional.empty();
		}
	}
}
