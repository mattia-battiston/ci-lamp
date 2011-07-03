package com.cilamp.service.services;

import java.net.MalformedURLException;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class JenkinsXmlParser {

  @Test
  public void parseBuildResult() throws MalformedURLException,
      DocumentException {
    URL url = new URL("http://localhost:8090/job/CILamp/lastBuild/api/xml");

    Document dom = new SAXReader().read(url);

    Element build = dom.getRootElement();
    Element building = build.element("building");
    Element result = build.element("result");
  }
}
