package com.pekelund.pekcodev2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TerminalToolsTest {

private final TerminalTools terminalTools = new TerminalTools();

@Test
void executeCommandCapturesOutput() {
String response = terminalTools.executeCommand("echo hello-agent");

assertThat(response).contains("Exit Code: 0");
assertThat(response).contains("StdOut:");
assertThat(response).contains("hello-agent");
assertThat(response).contains("StdErr:");
}
}
