package com.hacker.reader.domain;

import java.util.List;

public class Story {

  private String by;
  private Integer descendants;
  private Integer id;
  private List<Integer> kids = null;
  private Integer score;
  private Integer time;
  private String title;
  private String type;
  private String url;

  public String getBy() {
    return by;
  }

  public void setBy(String by) {
    this.by = by;
  }

  public Integer getDescendants() {
    return descendants;
  }

  public void setDescendants(Integer descendants) {
    this.descendants = descendants;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public List<Integer> getKids() {
    return kids;
  }

  public void setKids(List<Integer> kids) {
    this.kids = kids;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Integer getTime() {
    return time;
  }

  public void setTime(Integer time) {
    this.time = time;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
