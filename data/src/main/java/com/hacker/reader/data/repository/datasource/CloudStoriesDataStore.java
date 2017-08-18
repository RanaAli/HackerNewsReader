package com.hacker.reader.data.repository.datasource;

import com.hacker.reader.data.entity.StoryDetailEntity;
import com.hacker.reader.data.entity.StoryEntity;
import com.hacker.reader.data.net.services.StoriesApi;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Created by RanaAli on 8/11/17.
 */

public class CloudStoriesDataStore implements StoriesDataStore {

  private final StoriesApi storiesApi;

  /**
   * Construct a {@link StoriesDataStore} based on connections to the api (Cloud).
   *
   * @param storiesApi The {@link StoriesApi} implementation to use.
   */
  public CloudStoriesDataStore(StoriesApi storiesApi) {
    this.storiesApi = storiesApi;
  }

  @Override public Observable<List<Integer>> storiesEntityList() {
    return storiesApi.getAllStories()
        .observeOn(Schedulers.computation())
        .subscribeOn(Schedulers.computation());
  }

  @Override public Observable<StoryEntity> storyEntity(Integer storyID) {
    return storiesApi.getStory(storyID)
        .observeOn(Schedulers.computation())
        .subscribeOn(Schedulers.computation());
  }

  @Override public Observable<StoryDetailEntity> storyDetailEntity(Integer storyID) {
    return storiesApi.getStoryDetail(storyID)
        .observeOn(Schedulers.computation())
        .subscribeOn(Schedulers.computation());
  }
}
