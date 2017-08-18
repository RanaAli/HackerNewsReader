package com.hacker.reader.data.repository.datasource;

import com.hacker.reader.data.net.RestClient;
import com.hacker.reader.data.net.services.StoriesApi;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link StoriesDataStore}.
 */
@Singleton public class StoriesDataStoreFactory {

  @Inject public StoriesDataStoreFactory() {
  }

  /**
   * Create {@link StoriesDataStore} to retrieve data from the Cloud.
   */
  public StoriesDataStore createCloudDataStore() {
    final StoriesApi storiesApi = RestClient.getService(StoriesApi.class);

    return new CloudStoriesDataStore(storiesApi);
  }
}
