/*
 * 
 * 
 * Kapralov A.
 * 08.07.2010
 */

package kae.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/** @author A. Kapralov 08.07.2010 18:51:07 */
public class StreamUtils {

  public static final int BUFFER_SIZE = 10 * 1024;

  public static String readFile(String path) throws IOException {
    return readFile(new File(path));
  }

  public static String readFile(File file) throws IOException {
    FileInputStream stream = new FileInputStream(file);
    try {
      FileChannel fc = stream.getChannel();
      MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
      /* Instead of using default, pass in a decoder. */
      return Charset.defaultCharset().decode(bb).toString();
    } finally {
      stream.close();
    }
  }

  public static void writeFile(String path, String body) throws IOException {
    OutputStreamWriter writer = null;
    try {
      writer = new OutputStreamWriter(new FileOutputStream(path));
      writer.write(body);
    } finally {
      if (writer != null) {
        writer.close();
      }
    }
  }

  public static String readStream(InputStream inputStream) throws IOException {
    return readStream(inputStream, "UTF-8");
  }

  public static String readStream(InputStream inputStream, String charsetName) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charsetName));

    StringBuilder sb = new StringBuilder();
    char[] buf = new char[10 * 1024];
    int readed;
    while ((readed = reader.read(buf)) != -1) {
      sb.append(buf, 0, readed);
    }

    return sb.toString();
  }

  public static void transfuse(InputStream in, OutputStream out) throws IOException {
    byte[] buffer = new byte[BUFFER_SIZE];
    int readed;
    while (-1 != (readed = in.read(buffer))) {
      out.write(buffer, 0, readed);
    }
  }

  public static byte[] toByteArray(InputStream in) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
    transfuse(in, out);
    return out.toByteArray();
  }

}
