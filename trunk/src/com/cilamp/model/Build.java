package com.cilamp.model;

import java.util.Set;

public class Build {

  private String number;

  private String status;

  private String url;

  private Set<String> committers;

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Set<String> getCommitters() {
    return committers;
  }

  public void setCommitters(Set<String> committers) {
    this.committers = committers;
  }

}
