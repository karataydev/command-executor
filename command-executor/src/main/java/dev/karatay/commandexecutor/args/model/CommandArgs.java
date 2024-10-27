package dev.karatay.commandexecutor.args.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CommandArgs {
	// command arg to find in the config. first argument that starts without '-'
	private String command;
	// user defined args from the command line. args after the command arg. args[command.index:]
	private List<String> userArgs = new ArrayList<>();
	// system args for application use. anything before the command that starts with '-'. example: ./commandexecutor -config={} exCommand -arg2  
	private List<String> systemArgs = new ArrayList<>();
}
