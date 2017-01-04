package com.github.binarywang;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class App {
  public static void main(String[] args) throws IOException {
    // 写入数据
    String fileName = "test.txt";
    Path path = Files.createTempDirectory("abc");
    File file = new File(path.toString(), fileName);
    System.out.println(file.getAbsolutePath());

    if (!file.exists()) {
      file.createNewFile();
    }

    try (BufferedSink bufferSink = Okio.buffer(Okio.sink(file))) {
      bufferSink.writeString("this is some thing import \n", Charset.forName("utf-8"));
      bufferSink.writeString("this is also some thing import \n", Charset.forName("utf-8"));
    }

    //读取数据
    try (BufferedSource bufferedSource = Okio.buffer(Okio.source(file))) {
      String str = bufferedSource.readByteString().utf8();
      System.out.println("-->" + str);
    }
  }
}
