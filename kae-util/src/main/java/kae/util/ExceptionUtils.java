package kae.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * ExceptionUtils
 *
 * @author Kapralov A.
 *         11.03.2014 16:52
 */
public class ExceptionUtils {

  public static String dumpStackTrace(Throwable t) {
    StringWriter sw = new StringWriter();

    PrintWriter writer = new PrintWriter(sw);
    t.printStackTrace(writer);
    writer.close();

    return sw.toString();
  }

}
