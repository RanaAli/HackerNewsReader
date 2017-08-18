
package com.hacker.reader.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StoryTest {

  private static final int FAKE_ID = 8;

  private Story story;

  @Before
  public void setUp() {
    story = new Story();
    story.setId(FAKE_ID);
  }

  @Test
  public void testStoryConstructorHappyCase() {
    final int id = story.getId();

    assertThat(id).isEqualTo(FAKE_ID);
  }
}
