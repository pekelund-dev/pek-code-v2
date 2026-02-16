package com.pekelund.pekcodev2;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileToolsTest {

private final FileTools fileTools = new FileTools();

@Test
void writesAndReadsFile(@TempDir Path tempDir) throws Exception {
Path file = tempDir.resolve("sample.txt");

String writeResult = fileTools.writeFile(file.toString(), "hello world");

assertThat(writeResult).contains("Wrote 11 characters");
assertThat(Files.readString(file)).isEqualTo("hello world");
assertThat(fileTools.readFile(file.toString())).isEqualTo("hello world");
}
}
