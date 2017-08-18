package com.hacker.reader.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by RanaAli on 8/13/17.
 */

public class StoryDetailEntity {

  @SerializedName("by") @Expose private String by;
  @SerializedName("id") @Expose private Integer id;
  @SerializedName("kids") @Expose private List<Integer> kids = null;
  @SerializedName("parent") @Expose private Integer parent;
  @SerializedName("text") @Expose private String text;
  @SerializedName("time") @Expose private Integer time;
  @SerializedName("type") @Expose private String type;

  private List<StoryDetailEntity> replyList;

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

  public List<StoryDetailEntity> getReplyList() {
    return replyList;
  }

  public void setReplyList(List<StoryDetailEntity> replyList) {
    this.replyList = replyList;
  }
}
