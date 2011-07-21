package com.cilamp.service.services;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class PropertiesServiceTest {

  private PropertiesService propertiesService;

  @Before
  public void before() {
    propertiesService = new PropertiesService();
  }

  @Test
  public void readEndpoint() {
    assertNotNull(propertiesService.getEndpoint());
  }

  @Test
  public void readJobName() {
    assertNotNull(propertiesService.getJobName());
  }

  @Test
  public void readRefreshPeriod() {
    assertNotNull(propertiesService.getRefreshPeriod());
  }

}
