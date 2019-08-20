package com.ua.rush.javacode.system;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaDir {

  private Integer level;
  private String name;

  private Set<JavaDir> dirs = new HashSet<>();
  private Set<JavaFile> files = new HashSet<>();

  public JavaDir(java.io.File dir) {
    this(dir, 0);
  }

  private JavaDir(File dir, Integer level) {
    try {
      this.level = level;
      this.name = dir.getName();
      Files.list(dir.toPath())
          .map(Path::toFile)
          .forEach(file -> {
            if (file.isFile()) {
              if (file.getName().contains(".java")) {
                files.add(new JavaFile(file));
              }
            } else {
              dirs.add(new JavaDir(file, level + 1));
            }
          });
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public Integer getNumberOfLines() {
    Integer fileSum =
        files.stream()
            .map(JavaFile::getNumberOfLines)
            .reduce(0, Integer::sum);
    Integer dirSum =
        dirs.stream()
            .map(JavaDir::getNumberOfLines)
            .reduce(0, Integer::sum);
    return fileSum + dirSum;
  }

  public String toText() {
    StringBuilder sb = new StringBuilder();
    int dirOffset = level * 2;
    String dirSpaces =
        Stream.generate(() -> " ").limit(dirOffset).collect(Collectors.joining());
    String dirText = name + ": " + getNumberOfLines() + System.lineSeparator();
    sb.append(dirSpaces).append(dirText);
    files.forEach(file -> {
      int fileOffset = dirOffset + 2;
      String fileSpaces =
          Stream.generate(() -> " ").limit(fileOffset).collect(Collectors.joining());
      String fileText = file.toText();
      sb.append(fileSpaces).append(fileText);
    });
    dirs.forEach(dir ->
        sb.append(dir.toText())
    );
    return sb.toString();
  }
}
