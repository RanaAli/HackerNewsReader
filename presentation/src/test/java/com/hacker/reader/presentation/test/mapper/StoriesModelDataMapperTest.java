package com.hacker.reader.presentation.test.mapper;

import android.support.annotation.NonNull;
import com.hacker.reader.domain.Story;
import com.hacker.reader.domain.StoryDetail;
import com.hacker.reader.presentation.mapper.StoriesModelDataMapper;
import com.hacker.reader.presentation.model.StoryDetailModel;
import com.hacker.reader.presentation.model.StoryModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class) public class StoriesModelDataMapperTest {

  private static final String FAKE_STORY_1 = "Fake Story 1";
  private static final String FAKE_STORY_2 = "Fake Story 2";
  private static final String FAKE_STORY_3 = "Fake Story 3";
  private static final int FAKE_ID_1 = 1;
  private static final int FAKE_ID_2 = 2;
  private static final int FAKE_ID_3 = 3;

  private StoriesModelDataMapper storiesModelDataMapper;

  @Before public void setUp() throws Exception {
    storiesModelDataMapper = new StoriesModelDataMapper();
  }

  @Test public void testTransformStory() {
    ArrayList<Story> storyArrayList = new ArrayList<>();

    storyArrayList.add(getFakeStory(FAKE_ID_1, FAKE_STORY_1, Arrays.asList(1, 2, 3)));
    storyArrayList.add(getFakeStory(FAKE_ID_2, FAKE_STORY_2, Arrays.asList(1)));
    storyArrayList.add(getFakeStory(FAKE_ID_3, FAKE_STORY_3, null));

    Collection<StoryModel> storyModels = storiesModelDataMapper.transform(storyArrayList);

    List<StoryModel> storyModelList = (List<StoryModel>) storyModels;
    MatcherAssert.assertThat(storyModelList.size(), is(3));
    StoryModel storyModel = storyModelList.get(0);
    assertThat(storyModel, is(instanceOf(StoryModel.class)));
    assertThat(storyModel.getId(), is(FAKE_ID_1));
    assertThat(storyModel.getTitle(), is(FAKE_STORY_1));
    assertThat(storyModel.getKids(), is(instanceOf(List.class)));
    int kid = storyModel.getKids().get(0);
    assertThat(kid, is(instanceOf(int.class)));
    assertThat(kid, is(1));
  }

  @NonNull private Story getFakeStory(Integer id, String title, List<Integer> kids) {
    Story story = new Story();
    story.setId(id);
    story.setTitle(title);
    story.setKids(kids);
    return story;
  }

  @Test public void testTransformStoryDetails() {
    ArrayList<StoryDetail> storyDetailArrayList = new ArrayList<>();

    storyDetailArrayList.add(getFakeStoryDetail(FAKE_ID_1, FAKE_STORY_1, Arrays.asList(1, 2, 3)));
    storyDetailArrayList.add(getFakeStoryDetail(FAKE_ID_2, FAKE_STORY_2, Arrays.asList(1)));
    storyDetailArrayList.add(getFakeStoryDetail(FAKE_ID_3, FAKE_STORY_3, null));

    Collection<StoryDetailModel> storyDetailModels =
        storiesModelDataMapper.transformStoryDetails(storyDetailArrayList);

    List<StoryDetailModel> storyModelList = (List<StoryDetailModel>) storyDetailModels;
    MatcherAssert.assertThat(storyModelList.size(), is(3));
    StoryDetailModel storyModel = storyModelList.get(0);
    assertThat(storyModel, is(instanceOf(StoryDetailModel.class)));
    assertThat(storyModel.getId(), is(FAKE_ID_1));
    assertThat(storyModel.getText(), is(FAKE_STORY_1));
    assertThat(storyModel.getKids(), is(instanceOf(List.class)));
    int kid = storyModel.getKids().get(0);
    assertThat(kid, is(instanceOf(int.class)));
    assertThat(kid, is(1));
  }

  @NonNull private StoryDetail getFakeStoryDetail(Integer id, String title, List<Integer> kids) {
    StoryDetail storyDetail = new StoryDetail();
    storyDetail.setId(id);
    storyDetail.setText(title);
    storyDetail.setKids(kids);
    return storyDetail;
  }
}
