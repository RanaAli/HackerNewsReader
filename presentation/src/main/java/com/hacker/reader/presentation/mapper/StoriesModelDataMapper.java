package com.hacker.reader.presentation.mapper;

import com.hacker.reader.domain.Story;
import com.hacker.reader.domain.StoryDetail;
import com.hacker.reader.presentation.internal.di.PerActivity;
import com.hacker.reader.presentation.model.StoryDetailModel;
import com.hacker.reader.presentation.model.StoryModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Story} (in the domain layer) to {@link StoryModel} in the
 * presentation layer.
 */
@PerActivity public class StoriesModelDataMapper {

  @Inject public StoriesModelDataMapper() {
  }

  /**
   * Transform a Collection of {@link Story} into a Collection of {@link StoryModel}.
   *
   * @param stories Objects to be transformed.
   * @return List of {@link StoryModel}.
   */
  public Collection<StoryModel> transform(Collection<Story> stories) {
    Collection<StoryModel> storyModelCollection;

    if (stories != null && !stories.isEmpty()) {
      storyModelCollection = new ArrayList<>();
      for (Story story : stories) {

        StoryModel storyModel = new StoryModel();

        storyModel.setBy(story.getBy());
        storyModel.setDescendants(story.getDescendants());
        storyModel.setId(story.getId());
        storyModel.setKids(story.getKids());
        storyModel.setScore(story.getScore());
        storyModel.setTime(story.getTime());
        storyModel.setTitle(story.getTitle());
        storyModel.setType(story.getType());
        storyModel.setUrl(story.getUrl());

        storyModelCollection.add(storyModel);
      }
    } else {
      storyModelCollection = Collections.emptyList();
    }

    return storyModelCollection;
  }

  /**
   * Transform a Collection of {@link Story} into a Collection of {@link StoryModel}.
   *
   * @param storyDetails Objects to be transformed.
   * @return List of {@link StoryDetailModel}.
   */
  public Collection<StoryDetailModel> transformStoryDetails(Collection<StoryDetail> storyDetails) {
    Collection<StoryDetailModel> storyDetailModelCollection;

    if (storyDetails != null && !storyDetails.isEmpty()) {
      storyDetailModelCollection = new ArrayList<>();

      for (StoryDetail storyDetail : storyDetails) {
        StoryDetailModel storyDetailModel = new StoryDetailModel();

        storyDetailModel.setBy(storyDetail.getBy());
        storyDetailModel.setId(storyDetail.getId());
        storyDetailModel.setKids(storyDetail.getKids());
        storyDetailModel.setParent(storyDetail.getParent());
        storyDetailModel.setText(storyDetail.getText());
        storyDetailModel.setTime(storyDetail.getTime());
        storyDetailModel.setType(storyDetail.getType());

        List<StoryDetail> replies = storyDetail.getReply();
        if (replies != null && !replies.isEmpty()) {
          transformReplays(storyDetailModel, replies);
        }
        storyDetailModelCollection.add(storyDetailModel);
      }
    } else {
      storyDetailModelCollection = Collections.emptyList();
    }

    return storyDetailModelCollection;
  }

  private void transformReplays(StoryDetailModel storyDetailModel, List<StoryDetail> replies) {
    String replyString = "";
    for (StoryDetail reply : replies) {
      if (reply != null) {
        if (reply.getBy() != null && reply.getTime() != null) {
          replyString = replyString.concat(reply.getBy() + ": " + reply.getTime());
        }
        replyString = replyString.concat(" <br> <br> ");
        if (reply.getText() != null) {
          replyString = replyString.concat(reply.getText());
        }
        replyString = replyString.concat(" <br> <br> ");
      }
    }
    storyDetailModel.setReply(replyString);
  }
}

