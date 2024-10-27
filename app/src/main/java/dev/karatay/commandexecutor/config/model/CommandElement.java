package dev.karatay.commandexecutor.config.model;

import dev.karatay.commandexecutor.domain.model.ExecType;
import dev.karatay.commandexecutor.error.CommandErrors;
import lombok.Data;

@Data
public class CommandElement {
	private String commandName;
	private ExecType execType;
	private String value;


	public void validate() {
		if(execType == null) {
			throw CommandErrors.execTypeEmptyError(this);
		}
		if(value == null || value.isBlank()) {
			throw CommandErrors.commandValueEmptyError(this);
		}
	}
}
