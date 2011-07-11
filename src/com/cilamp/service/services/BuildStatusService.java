package com.cilamp.service.services;

import com.cilamp.model.Build;

public class BuildStatusService {

  // TODO get this from some properties service, investigate
  private String buildDataUrl = "http://localhost:8090/job/CILamp/lastCompletedBuild/api/xml";

  private DomRetriever domRetriever;

  public Build getLastCompletedBuildStatus() {

    domRetriever.getDom(buildDataUrl);

    return new Build();
  }

  public void setBuildDataUrl(String buildDataUrl) {
    this.buildDataUrl = buildDataUrl;
  }

  public void setDomRetriever(DomRetriever domRetriever) {
    this.domRetriever = domRetriever;

  }

}
