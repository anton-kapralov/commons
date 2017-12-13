/*
 *
 *
 * Kapralov A.
 * 08.07.2010
 */

package kae.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author A. Kapralov 08.07.2010 18:51:07
 */
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
      IOUtils.transfuse(fis, zos);
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
