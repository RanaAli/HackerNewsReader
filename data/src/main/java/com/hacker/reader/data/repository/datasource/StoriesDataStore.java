package com.hacker.reader.data.repository.datasource;

import com.hacker.reader.data.entity.StoryDetailEntity;
import com.hacker.reader.data.entity.StoryEntity;
import io.reactivex.Observable;
import java.util.List;

public interface StoriesDataStore {
  /**
   * Get an {@link Observable} which will emit a List of {@link Integer}s story IDz .
   */
  Observable<List<Integer>> storiesEntityList();

  /**
   * Get an {@link Observable} which will emit a {@link StoryEntity}.
   */
  Observable<StoryEntity> storyEntity(Integer storyID);

  /**
   * Get an {@link Observable} which will emit a details of {@link StoryDetailEntity}.
   */
  Observable<StoryDetailEntity> storyDetailEntity(Integer storyID);
}
