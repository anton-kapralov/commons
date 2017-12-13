package kae.util;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * IOUtils
 *
 * @author Kapralov A.
 * 17.04.2014 15:43
 */
public class IOUtils {

  public static final int BUFFER_SIZE = 10 * 1024;

  public static byte[] getBytes(File file) throws IOException {
    InputStream in = null;
    ByteArrayOutputStream out = null;
    try {
      in = new BufferedInputStream(new FileInputStream(file));
      out = new ByteArrayOutputStream();
      int b;
      while (-1 != (b = in.read())) {
        out.write(b);
      }
    } finally {
      if (in != null) {
        in.close();
      }
      if (out != null) {
        out.close();
      }
    }

    return out.toByteArray();
  }

  public static String getTextContent(String filepath) throws IOException {
    InputStreamReader in = null;
    StringBuilder sb = new StringBuilder();
    try {
      in = new InputStreamReader(new BufferedInputStream(new FileInputStream(filepath)));
      char c;
      while (-1 != (c = (char) in.read())) {
        sb.append(c);
      }
    } finally {
      if (in != null) {
        in.close();
      }
    }

    return sb.toString();
  }

  public static void write(byte[] bytes, String path) throws IOException {
    BufferedOutputStream out = null;
    try {
      out = new BufferedOutputStream(new FileOutputStream(path));
      out.write(bytes);
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }

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

  public static void closeQuitely(Closeable closeable) {
    if (closeable != null) {
      try {
        closeable.close();
      } catch (IOException e) {
        // Do nothing
      }
    }
  }

}
