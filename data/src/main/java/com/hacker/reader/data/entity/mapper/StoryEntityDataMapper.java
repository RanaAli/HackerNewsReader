package com.hacker.reader.data.entity.mapper;

import com.hacker.reader.data.entity.StoryDetailEntity;
import com.hacker.reader.data.entity.StoryEntity;
import com.hacker.reader.domain.Story;
import com.hacker.reader.domain.StoryDetail;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by RanaAli on 8/11/17.
 */
@Singleton public class StoryEntityDataMapper {

  @Inject public StoryEntityDataMapper() {
  }

  /**
   * Transform a StoriesEntity into an stories.
   *
   * @param storyEntity Object to be transformed.
   * @return {@link Story}
   */
  public Story transform(StoryEntity storyEntity) {
    Story story = null;

    if (storyEntity != null) {
      story = new Story();
      story.setBy(storyEntity.getBy());
      story.setDescendants(storyEntity.getDescendants());
      story.setId(storyEntity.getId());
      story.setKids(storyEntity.getKids());
      story.setScore(storyEntity.getScore());
      story.setTime(storyEntity.getTime());
      story.setTitle(storyEntity.getTitle());
      story.setType(storyEntity.getType());
      story.setUrl(storyEntity.getUrl());
    }
    return story;
  }

  /**
   * Transform a StoryDetailEntity into an stories.
   *
   * @param storyDetailEntity Object to be transformed.
   * @return {@link StoryDetail}
   */
  public StoryDetail transform(StoryDetailEntity storyDetailEntity) {
    StoryDetail storyDetail = null;

    if (storyDetailEntity != null) {
      storyDetail = new StoryDetail();

      storyDetail.setBy(storyDetailEntity.getBy());
      storyDetail.setId(storyDetailEntity.getId());
      storyDetail.setKids(storyDetailEntity.getKids());
      storyDetail.setParent(storyDetailEntity.getParent());
      storyDetail.setText(storyDetailEntity.getText());
      storyDetail.setTime(storyDetailEntity.getTime());
      storyDetail.setType(storyDetailEntity.getType());

      if (storyDetailEntity.getReplyList() != null) {
        storyDetail.setReply(transform(storyDetailEntity.getReplyList()));
      }
    }

    return storyDetail;
  }

  private List<StoryDetail> transform(List<StoryDetailEntity> storyDetailEntities) {
    List<StoryDetail> storyDetails = null;

    if (storyDetailEntities != null && !storyDetailEntities.isEmpty()) {
      storyDetails = new ArrayList<>();

      for (StoryDetailEntity storyDetailEntity : storyDetailEntities) {
        StoryDetail storyDetail = transform(storyDetailEntity);
        storyDetails.add(storyDetail);
      }
    }

    return storyDetails;
  }
}
