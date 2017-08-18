package com.hacker.reader.data.repository.datasource;

import com.hacker.reader.data.ApplicationTestCase;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class StoriesDataStoreFactoryTest extends ApplicationTestCase {

  private static final int FAKE_ID = 11;

  private StoriesDataStoreFactory storiesDataStoreFactory;

  @Before public void setUp() {
    storiesDataStoreFactory = new StoriesDataStoreFactory(RuntimeEnvironment.application);
  }

  @Test public void testCreateCloudDataStore() {

    StoriesDataStore storiesDataStore = storiesDataStoreFactory.createCloudDataStore();

    assertThat(storiesDataStore, is(notNullValue()));
    assertThat(storiesDataStore, is(instanceOf(CloudStoriesDataStore.class)));
  }
}
