package com.ua.rush.javacode;

import com.ua.rush.javacode.system.JavaDir;
import com.ua.rush.javacode.system.JavaFile;
import java.io.File;

public class Main {

  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Please add Java file/directory path parameter...");
    } else {
      try {
        File file = new File(args[0]);
        if (file.isFile()) {
          JavaFile javaFile = new JavaFile(file);
          System.out.println(javaFile.toText());
        } else {
          JavaDir javaDir = new JavaDir(file);
          System.out.println(javaDir.toText());
        }
      } catch (Exception e) {
        System.out.println("Oops! Something went wrong:");
        e.printStackTrace();
      }
    }
  }
}
