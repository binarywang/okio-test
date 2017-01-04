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
    Path path = Files.createTempFile("abc", "text");
    File file = path.toFile();
    System.out.println(file.getAbsolutePath());

    try (BufferedSink bufferSink = Okio.buffer(Okio.sink(file))) {
      bufferSink.writeUtf8("this is some thing import \n");
      bufferSink.writeString("this is also some thing import", Charset.forName("utf-8"));
    }

    //读取数据
    try (BufferedSource bufferedSource = Okio.buffer(Okio.source(file))) {
      String str = bufferedSource.readByteString().utf8();
      System.out.println("-->" + str);
    }
    try (BufferedSource bufferedSource = Okio.buffer(Okio.source(file))) {
      String str = bufferedSource.readUtf8Line();
      while (str != null) {
        System.out.println("-->" + str);
        str = bufferedSource.readUtf8Line();
      }
    }
  }
}
