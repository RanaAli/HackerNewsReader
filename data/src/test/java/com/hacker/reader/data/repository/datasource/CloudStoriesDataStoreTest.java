
package com.hacker.reader.data.repository.datasource;

import com.hacker.reader.data.entity.StoryDetailEntity;
import com.hacker.reader.data.entity.StoryEntity;
import com.hacker.reader.data.net.services.StoriesApi;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CloudStoriesDataStoreTest {

  private static final int FAKE_ID = 765;

  private CloudStoriesDataStore cloudStoriesDataStore;

  @Mock private StoriesApi storiesApi;

  @Before
  public void setUp() {
    cloudStoriesDataStore = new CloudStoriesDataStore(storiesApi);
  }

  @Test
  public void testGetStoriesEntityListFromApi() {
    List<Integer> integers = new ArrayList<>();
    Observable<List<Integer>> fakeObservable = Observable.just(integers);
    given(storiesApi.getAllStories()).willReturn(fakeObservable);

    cloudStoriesDataStore.storiesEntityList();

    verify(storiesApi).getAllStories();
  }

  @Test
  public void testGetStoryEntityFromApi() {
    StoryEntity storyEntity = new StoryEntity();
    Observable<StoryEntity> fakeObservable = Observable.just(storyEntity);
    given(storiesApi.getStory(FAKE_ID)).willReturn(fakeObservable);

    cloudStoriesDataStore.storyEntity(FAKE_ID);

    verify(storiesApi).getStory(FAKE_ID);
  }

  @Test
  public void testGetStoryEntityDetailsFromApi() {
    StoryDetailEntity storyDetailEntity = new StoryDetailEntity();
    Observable<StoryDetailEntity> fakeObservable = Observable.just(storyDetailEntity);
    given(storiesApi.getStoryDetail(FAKE_ID)).willReturn(fakeObservable);

    cloudStoriesDataStore.storyDetailEntity(FAKE_ID);

    verify(storiesApi).getStoryDetail(FAKE_ID);
  }
}
