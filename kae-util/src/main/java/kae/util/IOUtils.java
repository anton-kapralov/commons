package kae.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * IOUtils
 *
 * @author Kapralov A.
 *         17.04.2014 15:43
 */
public class IOUtils {

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
