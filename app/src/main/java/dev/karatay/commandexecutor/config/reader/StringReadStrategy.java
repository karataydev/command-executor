package dev.karatay.commandexecutor.config.reader;

import java.util.HashMap;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.karatay.commandexecutor.config.model.CommandElement;
import dev.karatay.commandexecutor.config.model.Config;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
class StringReadStrategy implements ConfigReadStrategy {
    
	private final String jsonConfig;
	private final ObjectMapper objectMapper;

    public StringReadStrategy(String jsonConfig) {
        this.jsonConfig = jsonConfig;
		this.objectMapper = new ObjectMapper();
    }


	@Override
	public Optional<Config> read() {
		try {
			var configMap = objectMapper.readValue(jsonConfig, new TypeReference<HashMap<String, CommandElement>>(){});
			var config = new Config(configMap);
			return Optional.of(config);
		} catch (Exception e) {
			log.error("Error parsing JSON configuration: {}", jsonConfig, e);
			return Optional.empty();
		}
	}
}
