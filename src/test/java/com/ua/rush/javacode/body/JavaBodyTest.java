package com.ua.rush.javacode.body;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class JavaBodyTest {

  @Test
  public void withoutEmptyLines() {
    //GIVEN
    String text =
        "line0"
            + System.lineSeparator()
            + "\r \t \r"
            + System.lineSeparator()
            + "line1";
    String[] source = text.split(System.lineSeparator());

    //WHEN
    JavaBody body = new JavaBody(text);
    String content = body.withoutEmptyLines().getBody();
    String[] actual = content.split(System.lineSeparator());

    //THEN
    assertEquals(source.length - 1, actual.length);
    assertEquals(source[0], actual[0]);
    assertEquals(source[2], actual[1]);
  }

  @Test
  public void withoutSingleLineComments() {
    //GIVEN
    String text =
        "line0"
            + System.lineSeparator()
            + "//comment"
            + System.lineSeparator()
            + "line2//comment";
    String[] source = text.split(System.lineSeparator());

    //WHEN
    JavaBody body = new JavaBody(text);
    String content = body.withoutSingleLineComments().getBody();
    String[] actual = content.split(System.lineSeparator());

    //THEN
    assertEquals(source.length, actual.length);
    assertEquals(source[0], actual[0]);
    assertEquals("", actual[1]);
    assertEquals("line2", actual[2]);
  }

  @Test
  public void withoutMultiLineComments() {
    //GIVEN
    String text =
        "line0"
            + System.lineSeparator()
            + "/*"
            + System.lineSeparator()
            + "comment"
            + System.lineSeparator()
            + "*/"
            + System.lineSeparator()
            + "/**/line4/***/";
    String[] source = text.split(System.lineSeparator());

    //WHEN
    JavaBody body = new JavaBody(text);
    String content = body.withoutMultiLineComments().getBody();
    String[] actual = content.split(System.lineSeparator());

    //THEN
    assertEquals(source.length - 2, actual.length);
    assertEquals(source[0], actual[0]);
    assertEquals("", actual[1]);
    assertEquals("line4", actual[2]);
  }

  @Test
  public void numberOfLines() {
    //GIVEN
    String text =
        "line0"
            + System.lineSeparator()
            + "line1"
            + System.lineSeparator()
            + "line2";

    //WHEN
    JavaBody body = new JavaBody(text);
    Integer actual = body.withoutEmptyLines().numberOfLines();

    //THEN
    assertEquals(3, actual);
  }
}