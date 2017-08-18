package com.hacker.reader.domain.interactor;

import com.hacker.reader.domain.StoryDetail;
import com.hacker.reader.domain.executor.PostExecutionThread;
import com.hacker.reader.domain.executor.ThreadExecutor;
import com.hacker.reader.domain.repository.StoriesRepository;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link StoryDetail}.
 */
public class GetStoryDetail extends UseCase<List<StoryDetail>, GetStoryDetail.Params> {

  private final StoriesRepository storiesRepository;

  @Inject GetStoryDetail(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
      StoriesRepository storiesRepository) {
    super(threadExecutor, postExecutionThread);
    this.storiesRepository = storiesRepository;
  }

  @Override Observable<List<StoryDetail>> buildUseCaseObservable(Params params) {
    return storiesRepository.storiesDetails(params.kids);
  }

  public static final class Params {

    private final List<Integer> kids;

    private Params(List<Integer> kids) {
      this.kids = kids;
    }

    public static Params forStory(List<Integer> id) {
      return new Params(id);
    }
  }
}
