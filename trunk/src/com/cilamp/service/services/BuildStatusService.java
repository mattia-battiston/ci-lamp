package com.cilamp.service.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;

import com.cilamp.event.BuildStatusLoadedEvent;
import com.cilamp.event.base.EventBus;
import com.cilamp.event.base.EventBusInstance;
import com.cilamp.model.Build;

public class BuildStatusService {

  // TODO get this from some properties service, investigate
  private String buildDataUrl = "http://localhost:8090/job/CILamp/lastCompletedBuild/api/xml";

  private DomRetriever domRetriever = new DomRetriever();

  private ErrorReporterService errorReporterService = new ErrorReporterService();

  private Document dom;

  private EventBus eventBus = EventBusInstance.getEventBus();

  public Build getLastCompletedBuildStatus() {
    try {
      return doGetLastCompletedBuildStatus();
    } catch (Exception e) {
      errorReporterService.notifyError(e);
      throw new RuntimeException(e);
    }
  }

  private Build doGetLastCompletedBuildStatus() {
    Build build = new Build();

    dom = domRetriever.getDom(buildDataUrl);

    build.setStatus(getDataFromDom("result"));
    build.setNumber(getDataFromDom("number"));
    build.setUrl(getDataFromDom("url"));
    build.setCommitters(getCommitters());

    notifyEveryoneBuildHasBeenLoaded(build);

    return build;
  }

  private void notifyEveryoneBuildHasBeenLoaded(Build build) {
    eventBus.fireEvent(new BuildStatusLoadedEvent(build));
  }

  @SuppressWarnings("unchecked")
  private Set<String> getCommitters() {
    Set<String> committers = new HashSet<String>();
    Element changeSet = dom.getRootElement().element("changeSet");
    List<Element> changes = changeSet.elements("item");
    if (noChanges(changes))
      return Collections.EMPTY_SET;

    return getUsersOfChanges(committers, changes);
  }

  private Set<String> getUsersOfChanges(Set<String> committers,
      List<Element> changes) {
    for (Element item : changes) {
      committers.add(item.element("user").getText());
    }
    return committers;
  }

  private boolean noChanges(List<Element> changes) {
    return changes == null;
  }

  private String getDataFromDom(String elementName) {
    Element element = dom.getRootElement().element(elementName);
    if (element == null)
      return "";
    return element.getText();
  }

  public void setBuildDataUrl(String buildDataUrl) {
    this.buildDataUrl = buildDataUrl;
  }

  public void setDomRetriever(DomRetriever domRetriever) {
    this.domRetriever = domRetriever;

  }

  public void setEventBus(EventBus eventBus) {
    this.eventBus = eventBus;
  }

  public void setErrorReporterService(ErrorReporterService errorReporterService) {
    this.errorReporterService = errorReporterService;
  }

}
