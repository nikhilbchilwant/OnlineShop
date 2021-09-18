package com.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class MainITest {

  Path orderPath;
  File orderFile;

  @TempDir Path tempDir;

  @BeforeEach
  public void setUp() {
    try {
      orderPath = tempDir.resolve("orders.txt");
    } catch (InvalidPathException ipe) {
      ipe.printStackTrace();
    }

    orderFile = orderPath.toFile();
  }

  @Test
  public void testMain() {
    BufferedWriter bufferedWriter;
    try (FileWriter writer = new FileWriter(orderFile)) {
      bufferedWriter = new BufferedWriter(writer);
      bufferedWriter.write("1 book at 12.49");
      bufferedWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    OnlineShop.main(new String[] {orderFile.getAbsolutePath()});
  }
}
