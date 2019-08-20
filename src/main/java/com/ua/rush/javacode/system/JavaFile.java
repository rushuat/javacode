package com.ua.rush.javacode.system;

import com.ua.rush.javacode.body.JavaBody;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;

public class JavaFile {

  private String name;
  private JavaBody body;

  public JavaFile(java.io.File file) {
    try (
        FileInputStream fis = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(fis)
    ) {
      byte[] data = new byte[(int) file.length()];
      dis.readFully(data);
      body = new JavaBody(data);
      name = file.getName();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public Integer getNumberOfLines() {
    return
        body
            .withoutMultiLineComments()
            .withoutSingleLineComments()
            .withoutEmptyLines()
            .numberOfLines();
  }

  public String toText() {
    return name + ": " + getNumberOfLines() + System.lineSeparator();
  }
}
