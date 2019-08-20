package com.ua.rush.javacode.system;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.Test;

public class JavaDirTest {

  @Test
  public void getNumberOfLines() {
    //GIVEN
    File root = new File("root");

    File file = new File("root/Root.java");
    File main = new File("root/main");
    File test = new File("root/test");

    //WHEN
    JavaDir rootDir = new JavaDir(root);

    JavaFile rootFile = new JavaFile(file);
    JavaDir mainDir = new JavaDir(main);
    JavaDir testDir = new JavaDir(test);

    Integer expected =
        rootFile.getNumberOfLines() + mainDir.getNumberOfLines() + testDir.getNumberOfLines();

    //THEN
    assertEquals(expected, rootDir.getNumberOfLines());
  }

  @Test
  public void toText() {
    //GIVEN
    File root = new File("root");

    //WHEN
    JavaDir rootDir = new JavaDir(root);

    Long actual = rootDir.toText().chars().filter(ch -> ch == ' ').count();

    //THEN
    assertEquals(16 + 7, actual);
  }
}