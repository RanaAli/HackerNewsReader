package com.hacker.reader.data.repository;

import com.hacker.reader.data.entity.StoryDetailEntity;
import com.hacker.reader.data.entity.mapper.StoryEntityDataMapper;
import com.hacker.reader.data.repository.datasource.StoriesDataStore;
import com.hacker.reader.data.repository.datasource.StoriesDataStoreFactory;
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

@RunWith(MockitoJUnitRunner.class) public class StoriesDataRepositoryTest {

  private static final int FAKE_ID = 123;

  private StoriesDataRepository storiesDataRepository;

  @Mock private StoriesDataStoreFactory mockStoriesDataStoreFactory;
  @Mock private StoryEntityDataMapper mockUStoryEntityDataMapper;
  @Mock private StoriesDataStore mockStoriesDataStore;

  @Before public void setUp() {
    storiesDataRepository =
        new StoriesDataRepository(mockStoriesDataStoreFactory, mockUStoryEntityDataMapper);
    given(mockStoriesDataStoreFactory.createCloudDataStore()).willReturn(mockStoriesDataStore);
  }

  @Test public void testGetStoryListCase() {
    List<Integer> storyList = new ArrayList<>();
    storyList.add(new Integer(0));
    given(mockStoriesDataStore.storiesEntityList()).willReturn(Observable.just(storyList));

    storiesDataRepository.stories();

    verify(mockStoriesDataStoreFactory).createCloudDataStore();
    verify(mockStoriesDataStore).storiesEntityList();
  }

  @Test public void testGetStoryDetailCase() {
    StoryDetailEntity storyDetailEntity = new StoryDetailEntity();
    given(mockStoriesDataStore.storyDetailEntity(FAKE_ID)).willReturn(
        Observable.just(storyDetailEntity));

    storiesDataRepository.storyDetails(FAKE_ID);

    verify(mockStoriesDataStoreFactory).createCloudDataStore();
    verify(mockStoriesDataStore).storyDetailEntity(FAKE_ID);
  }
}



