package com.cilamp.service.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

import java.net.URL;

import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DomRetrieverTest {

  DomRetriever domRetriever = new DomRetriever();

  @Mock
  private SAXReader reader;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    domRetriever.setReader(reader);
  }

  @Test
  public void readContentOfUrl() throws DocumentException {
    domRetriever.getDom("http://www.url.com");

    String url = getUrlRead();
    assertThat(url, is("http://www.url.com"));
  }

  private String getUrlRead() throws DocumentException {
    ArgumentCaptor<URL> urlCaptor = ArgumentCaptor.forClass(URL.class);
    verify(reader).read(urlCaptor.capture());
    return urlCaptor.getValue().toString();
  }

}
