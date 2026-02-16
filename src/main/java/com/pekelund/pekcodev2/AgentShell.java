package com.pekelund.pekcodev2;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AgentShell {

private final ChatClient chatClient;

public AgentShell(ChatClient chatClient) {
this.chatClient = chatClient;
}

@ShellMethod(key = { "agent", "code" }, value = "Run the coding agent prompt")
public String agent(String prompt) {
return this.chatClient.prompt()
.user(prompt)
.tools("executeCommand", "readFile", "writeFile")
.call()
.content();
}
}
