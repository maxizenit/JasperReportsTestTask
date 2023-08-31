package ru.maxizenit.util;

import java.io.File;

public class FileUtils {

  private static final String TICKETS = "tickets.json";

  private static final String REPORT_PATTERN = "reportPattern.jrxml";

  private static File getFileByName(String fileName) {
    return new File(FileUtils.class.getClassLoader().getResource(fileName).getFile());
  }

  public static File getTicketsFile() {
    return getFileByName(TICKETS);
  }

  public static File getReportPatternFile() {
    return getFileByName(REPORT_PATTERN);
  }
}
