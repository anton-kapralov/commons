/*
 * 
 * 
 * Kapralov A.
 * 08.07.2010
 */

package kae.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/** @author A. Kapralov 08.07.2010 18:51:07 */
public class FileUtils {

  public static String getExtension(String fileName) {
    int lastDotIdx = fileName.lastIndexOf('.');
    if (lastDotIdx != -1) {
      String extension = fileName.substring(lastDotIdx + 1);
      if (extension != null && !extension.isEmpty()) {
        return extension;
      }
    }

    return null;
  }

  public static String getExtension(File file) {
    return getExtension(file.getName());
  }

  public static String getNameWithoutExtension(String fileName) {
    int lastDotIdx = fileName.lastIndexOf('.');
    if (lastDotIdx != -1) {
      return fileName.substring(0, lastDotIdx);
    } else {
      return fileName;
    }
  }

  public static String getNameWithoutExtension(File file) {
    return getNameWithoutExtension(file.getName());
  }

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

  public static boolean remove(String path) {
    return new File(path).delete();
  }

  public static File archivate(File sourceFile, String sourceFileName,
      File destinationDir, String destinationFileName) throws IOException {
    File archiveFile = new File(destinationDir, destinationFileName + ".zip");

    FileInputStream fis = null;
    ZipOutputStream zos = null;
    try {
      fis = new FileInputStream(sourceFile);
      zos = new ZipOutputStream(new FileOutputStream(archiveFile));

      zos.putNextEntry(new ZipEntry(sourceFileName));
      StreamUtils.transfuse(fis, zos);
      zos.close();
    } finally {
      if (fis != null) {
        fis.close();
      }
      if (zos != null) {
        zos.close();
      }
    }

    return archiveFile;
  }

  public static File archivate(File sourceFile, File destinationDir) throws IOException {
    final String filename = getNameWithoutExtension(sourceFile);
    return archivate(sourceFile, filename, destinationDir, filename);
  }

}
