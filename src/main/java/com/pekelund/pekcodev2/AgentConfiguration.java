package com.pekelund.pekcodev2;

import java.util.function.Function;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

@Configuration
public class AgentConfiguration {

	public static final String SYSTEM_PROMPT = "You are an expert Autonomous Coding Agent. You are running in a terminal. "
			+ "You have access to the file system and shell. You can run compilations, git commands, and edit code. "
			+ "Always check the output of your commands before proceeding.";
	public static final String EXECUTE_COMMAND_TOOL = "executeCommand";
	public static final String READ_FILE_TOOL = "readFile";
	public static final String WRITE_FILE_TOOL = "writeFile";

@Bean
ChatClient chatClient(ChatClient.Builder builder) {
return builder.defaultSystem(SYSTEM_PROMPT).build();
}

	@Bean(name = EXECUTE_COMMAND_TOOL)
	@Description("Execute a shell command in the current terminal and return exit code, stdout, and stderr")
	Function<String, String> executeCommand(TerminalTools terminalTools) {
		return terminalTools::executeCommand;
	}

	@Bean(name = READ_FILE_TOOL)
	@Description("Read and return the content of a file from disk using its absolute or relative path")
	Function<String, String> readFile(FileTools fileTools) {
		return fileTools::readFile;
	}

	@Bean(name = WRITE_FILE_TOOL)
	@Description("Write content to a file path. Input JSON must include path and content fields")
	Function<WriteFileRequest, String> writeFile(FileTools fileTools) {
		return request -> fileTools.writeFile(request.path(), request.content());
}

record WriteFileRequest(String path, String content) {
}
}
