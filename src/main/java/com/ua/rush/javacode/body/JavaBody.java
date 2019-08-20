package com.ua.rush.javacode.body;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaBody {

  private static final String LINE_SEPARATOR = System.lineSeparator();
  private static final String SINGLE_LINE_COMMENT = "//(.*?)$|//(.*?)" + LINE_SEPARATOR;
  private static final String MULTI_LINE_COMMENT = "/\\*([\\s\\S]*?)\\*/";

  private String body;

  public JavaBody(byte[] data) {
    body = new String(data);
  }

  public JavaBody(String text) {
    body = text;
  }

  public JavaBody withoutEmptyLines() {
    return
        new JavaBody(
            Stream.of(body.split(LINE_SEPARATOR))
                .filter(line -> !line.trim().isEmpty())
                .collect(Collectors.joining(LINE_SEPARATOR))
        );
  }

  public JavaBody withoutSingleLineComments() {
    return new JavaBody(body.replaceAll(SINGLE_LINE_COMMENT, LINE_SEPARATOR));
  }

  public JavaBody withoutMultiLineComments() {
    return new JavaBody(body.replaceAll(MULTI_LINE_COMMENT, ""));
  }

  public Integer numberOfLines() {
    return body.split(LINE_SEPARATOR).length;
  }

  public String getBody() {
    return body;
  }
}
