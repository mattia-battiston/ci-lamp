package com.cilamp.model;

import java.util.List;

public class Build {

  private String number;

  private String status;

  private String url;

  private List<String> committers;

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

  public List<String> getCommitters() {
    return committers;
  }

  public void setCommitters(List<String> committers) {
    this.committers = committers;
  }

}