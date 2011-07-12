package com.cilamp.service.services;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cilamp.model.Build;

public class BuildStatusServiceTest {

  private BuildStatusService buildStatusService = new BuildStatusService();

  @Mock
  private DomRetriever domRetriever;
  @Mock
  Document dom;
  @Mock
  Element build;
  @Mock
  Element result;
  @Mock
  Element number;
  @Mock
  Element url;
  @Mock
  Element changeSet;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    buildStatusService.setDomRetriever(domRetriever);
    mockDom();
  }

  @Test
  public void retrievesDomOfUrl() {
    buildStatusService.setBuildDataUrl("www.buildurl.com");

    buildStatusService.getLastCompletedBuildStatus();

    verify(domRetriever).getDom("www.buildurl.com");
  }

  @Test
  public void readResultFromDom() {
    setResult("SUCCESS");

    Build buildStatus = buildStatusService.getLastCompletedBuildStatus();

    assertThat(buildStatus.getStatus(), is("SUCCESS"));
  }

  @Test
  public void readNumberFromDom() {
    setNumber("123");

    Build buildStatus = buildStatusService.getLastCompletedBuildStatus();

    assertThat(buildStatus.getNumber(), is("123"));
  }

  @Test
  public void readUrlFromDom() {
    setUrl("www.jenkins.com/myBuild");

    Build buildStatus = buildStatusService.getLastCompletedBuildStatus();

    assertThat(buildStatus.getUrl(), is("www.jenkins.com/myBuild"));
  }

  @Test
  public void readAllCommitters() {
    setCommitters("user1", "user2", "user1", "user3");

    Build buildStatus = buildStatusService.getLastCompletedBuildStatus();

    Set<String> committers = buildStatus.getCommitters();
    assertThat(committers.size(), is(3));
    assertTrue(committers.contains("user1"));
    assertTrue(committers.contains("user2"));
    assertTrue(committers.contains("user3"));
  }

  private void setCommitters(String... committersNames) {
    ArrayList<Element> committers = new ArrayList<Element>();
    for (String committerName : committersNames) {
      committers.add(committer(committerName));
    }
    when(changeSet.elements("item")).thenReturn(committers);
  }

  private Element committer(String name) {
    Element item = mock(Element.class);
    Element user = mock(Element.class);
    when(item.element("user")).thenReturn(user);
    when(user.getText()).thenReturn(name);
    return item;
  }

  private void setUrl(String data) {
    when(url.getText()).thenReturn(data);
  }

  private void setNumber(String data) {
    when(number.getText()).thenReturn(data);
  }

  private void setResult(String data) {
    when(result.getText()).thenReturn(data);
  }

  private void mockDom() {
    when(domRetriever.getDom(anyString())).thenReturn(dom);
    when(dom.getRootElement()).thenReturn(build);
    when(build.element("result")).thenReturn(result);
    when(build.element("number")).thenReturn(number);
    when(build.element("url")).thenReturn(url);
    when(build.element("changeSet")).thenReturn(changeSet);
    when(changeSet.elements("item")).thenReturn(new ArrayList<Element>());
  }

}
