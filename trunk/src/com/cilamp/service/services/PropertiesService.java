package com.cilamp.service.services;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesService {

  final static Logger log = LoggerFactory.getLogger(PropertiesService.class);

  private static Properties properties = new Properties();

  static {
    initialize();
  }

  private static void initialize() {
    log.info("initializing properties...");
    URL url = ClassLoader.getSystemResource("cilamp.properties");
    try {
      properties.load(url.openStream());
      log.info("properties read: {}", properties);
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

  public String getSerialPortName() {
    return properties.getProperty("serialportname");
  }
}
