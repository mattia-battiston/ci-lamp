package com.cilamp.service.services;

import org.dom4j.Document;

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

    return build;
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
