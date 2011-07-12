package com.cilamp.service.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;

import com.cilamp.model.Build;

public class BuildStatusService {

  // TODO get this from some properties service, investigate
  private String buildDataUrl = "http://localhost:8090/job/CILamp/lastCompletedBuild/api/xml";

  private DomRetriever domRetriever;

  private Document dom;

  public Build getLastCompletedBuildStatus() {

    // TODO what do we do if information is not present?

    Build build = new Build();

    dom = domRetriever.getDom(buildDataUrl);

    build.setStatus(getDataFromDom("result"));
    build.setNumber(getDataFromDom("number"));
    build.setUrl(getDataFromDom("url"));
    build.setCommitters(getCommitters());

    return build;
  }

  @SuppressWarnings("unchecked")
  private Set<String> getCommitters() {
    Set<String> committers = new HashSet<String>();
    Element changeSet = dom.getRootElement().element("changeSet");
    List<Element> items = changeSet.elements("item");
    if (items == null)
      return Collections.EMPTY_SET;

    for (Element item : items) {
      committers.add(item.element("user").getText());
    }
    return committers;
  }

  private String getDataFromDom(String elementName) {
    return dom.getRootElement().element(elementName).getText();
  }

  public void setBuildDataUrl(String buildDataUrl) {
    this.buildDataUrl = buildDataUrl;
  }

  public void setDomRetriever(DomRetriever domRetriever) {
    this.domRetriever = domRetriever;

  }

}
