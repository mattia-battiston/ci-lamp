package com.cilamp.service.services;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertiesService {

  private static Properties properties = new Properties();

  static {
    initialize();
  }

  private static void initialize() {
    URL url = ClassLoader.getSystemResource("cilamp.properties");
    try {
      properties.load(url.openStream());
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public String getEndpoint() {
    return properties.getProperty("endpoint");
  }

  public String getJobName() {
    return properties.getProperty("jobname");
  }

  public Long getRefreshPeriod() {
    return Long.parseLong(properties.getProperty("refreshperiod"));
  }
}
