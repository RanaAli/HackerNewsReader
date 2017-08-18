package com.hacker.reader.domain.interactor;

import com.hacker.reader.domain.Story;
import com.hacker.reader.domain.executor.PostExecutionThread;
import com.hacker.reader.domain.executor.ThreadExecutor;
import com.hacker.reader.domain.repository.StoriesRepository;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all Stories.
 */
public class GetStoriesList extends UseCase<List<Story>, Void> {

  private final StoriesRepository storiesRepository;

  @Inject GetStoriesList(StoriesRepository storiesRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.storiesRepository = storiesRepository;
  }

  @Override Observable<List<Story>> buildUseCaseObservable(Void aVoid) {
    return this.storiesRepository.stories();
  }
}
