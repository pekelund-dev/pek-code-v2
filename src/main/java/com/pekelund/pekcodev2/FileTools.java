package com.pekelund.pekcodev2;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Component;

@Component
public class FileTools {

public String readFile(String path) {
try {
return Files.readString(Path.of(path), StandardCharsets.UTF_8);
}
catch (IOException e) {
throw new UncheckedIOException("Failed to read file: " + path, e);
}
}

public String writeFile(String path, String content) {
try {
Path filePath = Path.of(path);
if (filePath.getParent() != null) {
Files.createDirectories(filePath.getParent());
}
Files.writeString(filePath, content, StandardCharsets.UTF_8);
return "Wrote " + content.length() + " characters to " + path;
}
catch (IOException e) {
throw new UncheckedIOException("Failed to write file: " + path, e);
}
}
}
