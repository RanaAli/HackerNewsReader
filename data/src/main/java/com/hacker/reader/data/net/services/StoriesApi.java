package com.hacker.reader.data.net.services;

import com.hacker.reader.data.entity.StoryDetailEntity;
import com.hacker.reader.data.entity.StoryEntity;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by RanaAli on 8/6/17.
 */

public interface StoriesApi {

  String GET_TOP_STORIES = "topstories.json";
  String GET_STORY_DETAIL = "item/{storyID}.json";

  @GET(GET_TOP_STORIES) Observable<List<Integer>> getAllStories();

  @GET(GET_STORY_DETAIL) Observable<StoryEntity> getStory(@Path("storyID") Integer storyID);

  @GET(GET_STORY_DETAIL) Observable<StoryDetailEntity> getStoryDetail(
      @Path("storyID") Integer storyID);
}
