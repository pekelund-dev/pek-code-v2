package com.pekelund.pekcodev2;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

@Component
public class TerminalTools {

	public String executeCommand(String command) {
		try {
			String[] prefix = shellPrefix();
			ProcessBuilder processBuilder = new ProcessBuilder(prefix[0], prefix[1], command);
Process process = processBuilder.start();

CompletableFuture<String> stdoutFuture = CompletableFuture.supplyAsync(
() -> readStream(process.getInputStream()));
CompletableFuture<String> stderrFuture = CompletableFuture.supplyAsync(
() -> readStream(process.getErrorStream()));

int exitCode = process.waitFor();
String stdout = stdoutFuture.join();
String stderr = stderrFuture.join();

return "Exit Code: " + exitCode + System.lineSeparator()
+ "StdOut:" + System.lineSeparator() + stdout + System.lineSeparator()
+ "StdErr:" + System.lineSeparator() + stderr;
}
catch (IOException e) {
throw new UncheckedIOException("Failed to execute command", e);
}
catch (InterruptedException e) {
Thread.currentThread().interrupt();
throw new IllegalStateException("Command execution interrupted", e);
}
}

private String[] shellPrefix() {
if (System.getProperty("os.name").toLowerCase().contains("win")) {
return new String[] { "cmd", "/c" };
}
return new String[] { "sh", "-c" };
}

private String readStream(java.io.InputStream inputStream) {
try {
return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
}
catch (IOException e) {
throw new UncheckedIOException(e);
}
}
}
