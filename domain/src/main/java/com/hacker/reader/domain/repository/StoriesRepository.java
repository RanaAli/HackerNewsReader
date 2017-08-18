package com.hacker.reader.domain.repository;

import com.hacker.reader.domain.Story;
import com.hacker.reader.domain.StoryDetail;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import java.util.List;

/**
 * Interface that represents a Repository for getting Stories related data.
 */
public interface StoriesRepository {
  /**
   * Get an {@link Observable} which will emit a List of Stories.
   */
  Observable<List<Story>> stories();

  /**
   * Get an {@link Observable} which will emit detail info of given story IDz.
   */
  Observable<List<StoryDetail>> storiesDetails(List<Integer> storyIDs);

  /**
   * Get an {@link Observable} which will emit detail info of given story ID.
   */
  ObservableSource<StoryDetail> storyDetails(Integer storyID);
}
