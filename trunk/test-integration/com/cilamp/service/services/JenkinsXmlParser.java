package com.cilamp.service.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class JenkinsXmlParser {

  @Test
  public void parseBuildResult() throws MalformedURLException,
      DocumentException {
    URL url = new URL(
        "http://localhost:8090/job/CILamp/lastCompletedBuild/api/xml");

    Document dom = new SAXReader().read(url);

    Element build = dom.getRootElement();

    Element result = build.element("result");
    System.out.println(result.getData());

    Set<String> users = new HashSet<String>();
    Element changeSet = build.element("changeSet");
    List<Element> elements = changeSet.elements("item");
    for (Element item : elements) {
      users.add(item.element("user").getText());
    }
    System.out.println(users);

  }
}
