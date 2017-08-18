package com.hacker.reader.domain;

import java.util.List;

/**
 * Created by RanaAli on 8/13/17.
 */

public class StoryDetail {

  private String by;
  private Integer id;
  private List<Integer> kids = null;
  private Integer parent;
  private String text;
  private Integer time;
  private String type;
  private List<StoryDetail> reply;

  public String getBy() {
    return by;
  }

  public void setBy(String by) {
    this.by = by;
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

  public Integer getParent() {
    return parent;
  }

  public void setParent(Integer parent) {
    this.parent = parent;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Integer getTime() {
    return time;
  }

  public void setTime(Integer time) {
    this.time = time;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<StoryDetail> getReply() {
    return reply;
  }

  public void setReply(List<StoryDetail> reply) {
    this.reply = reply;
  }
}
