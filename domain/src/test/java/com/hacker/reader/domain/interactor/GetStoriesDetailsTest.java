package com.hacker.reader.domain.interactor;

import com.hacker.reader.domain.executor.PostExecutionThread;
import com.hacker.reader.domain.executor.ThreadExecutor;
import com.hacker.reader.domain.repository.StoriesRepository;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class) public class GetStoriesDetailsTest {

  private static final int ID = 123;

  private GetStoryDetail getStoryDetail;

  @Mock private StoriesRepository mockStoriesRepository;
  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;

  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Before public void setUp() {
    getStoryDetail =
        new GetStoryDetail(mockThreadExecutor, mockPostExecutionThread, mockStoriesRepository);
  }

  @Test public void testGetStoriesDetailsUseCaseObservable() {
    ArrayList<Integer> idsList = new ArrayList<>();
    idsList.add(ID);

    getStoryDetail.buildUseCaseObservable(GetStoryDetail.Params.forStory(idsList));

    verify(mockStoriesRepository).storiesDetails(idsList);
    verifyNoMoreInteractions(mockStoriesRepository);
    verifyZeroInteractions(mockPostExecutionThread);
    verifyZeroInteractions(mockThreadExecutor);
  }

  @Test public void testShouldFailWhenNoOrEmptyParameters() {
    expectedException.expect(NullPointerException.class);
    getStoryDetail.buildUseCaseObservable(null);
  }
}
