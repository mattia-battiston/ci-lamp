package com.cilamp.service.services;

import java.net.MalformedURLException;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class DomRetriever {

  private SAXReader reader = new SAXReader();

  public Document getDom(String endpoint) {
    try {
      return doGetDom(endpoint);
    } catch (Exception e) {
      throw new RuntimeException("Error reading document at " + endpoint + ": "
          + e.getMessage(), e);
    }
  }

  private Document doGetDom(String endpoint) throws MalformedURLException,
      DocumentException {
    URL url = new URL(endpoint);

    return reader.read(url);
  }

  public void setReader(SAXReader reader) {
    this.reader = reader;
  }

}
