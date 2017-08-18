package com.hacker.reader.domain.interactor;

import com.hacker.reader.domain.executor.PostExecutionThread;
import com.hacker.reader.domain.executor.ThreadExecutor;
import com.hacker.reader.domain.repository.StoriesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class) public class GetStoriesListTest {

  private GetStoriesList getStoriesList;

  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;
  @Mock private StoriesRepository mockStoriesRepository;

  @Before public void setUp() {
    getStoriesList =
        new GetStoriesList(mockStoriesRepository,
            mockThreadExecutor, mockPostExecutionThread);
  }

  @Test public void testGetStoriesListUseCaseObservableCase() {
    getStoriesList.buildUseCaseObservable(null);

    verify(mockStoriesRepository).stories();
    verifyNoMoreInteractions(mockStoriesRepository);
    verifyZeroInteractions(mockThreadExecutor);
    verifyZeroInteractions(mockPostExecutionThread);
  }
}
