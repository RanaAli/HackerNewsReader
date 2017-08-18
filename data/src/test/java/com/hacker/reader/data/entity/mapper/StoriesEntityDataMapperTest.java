package com.hacker.reader.data.entity.mapper;

import com.hacker.reader.data.entity.StoryDetailEntity;
import com.hacker.reader.data.entity.StoryEntity;
import com.hacker.reader.domain.Story;
import com.hacker.reader.domain.StoryDetail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class) public class StoriesEntityDataMapperTest {

  private static final int FAKE_ID = 123;
  private static final String FAKE_TITLE = "Tony Stark";

  private StoryEntityDataMapper storyEntityDataMapper;

  @Before public void setUp() throws Exception {
    storyEntityDataMapper = new StoryEntityDataMapper();
  }

  @Test public void testTransformStoryEntity() {
    StoryEntity storyEntity = new StoryEntity();
    storyEntity.setId(FAKE_ID);
    storyEntity.setTitle(FAKE_TITLE);

    Story story = storyEntityDataMapper.transform(storyEntity);

    assertThat(story, is(instanceOf(Story.class)));
    assertThat(story.getId(), is(FAKE_ID));
    assertThat(story.getTitle(), is(FAKE_TITLE));
  }

  @Test public void testTransformStoryDetailEntityCollection() {
    StoryDetailEntity storyDetailEntity = new StoryDetailEntity();
    storyDetailEntity.setId(FAKE_ID);
    storyDetailEntity.setText(FAKE_TITLE);

    StoryDetail storyDetail = storyEntityDataMapper.transform(storyDetailEntity);

    assertThat(storyDetail, is(instanceOf(StoryDetail.class)));
    assertThat(storyDetail.getId(), is(FAKE_ID));
    assertThat(storyDetail.getText(), is(FAKE_TITLE));
  }
}
