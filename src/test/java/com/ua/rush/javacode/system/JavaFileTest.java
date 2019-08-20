package com.ua.rush.javacode.system;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.Test;

public class JavaFileTest {

  @Test
  public void getNumberOfLines() {
    //GIVEN
    File root = new File("root/Root.java");
    File main = new File("root/main/Main.java");

    //WHEN
    JavaFile rootFile = new JavaFile(root);
    JavaFile mainFile = new JavaFile(main);

    //THEN
    assertEquals(3, rootFile.getNumberOfLines());
    assertEquals(5, mainFile.getNumberOfLines());
  }

  @Test
  public void toText() {
    //GIVEN
    File test = new File("root/test/Test.java");

    //WHEN
    JavaFile testFile = new JavaFile(test);

    //THEN
    assertEquals("Test.java: 5" + System.lineSeparator(), testFile.toText());
  }
}