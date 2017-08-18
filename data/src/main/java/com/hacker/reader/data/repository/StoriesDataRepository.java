package com.hacker.reader.data.repository;

import com.hacker.reader.data.entity.StoryDetailEntity;
import com.hacker.reader.data.entity.mapper.StoryEntityDataMapper;
import com.hacker.reader.data.repository.datasource.StoriesDataStore;
import com.hacker.reader.data.repository.datasource.StoriesDataStoreFactory;
import com.hacker.reader.domain.Story;
import com.hacker.reader.domain.StoryDetail;
import com.hacker.reader.domain.repository.StoriesRepository;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link StoriesRepository} for retrieving Stories data.
 */
@Singleton public class StoriesDataRepository implements StoriesRepository {

  private final StoriesDataStoreFactory storiesDataStoreFactory;
  private final StoryEntityDataMapper storyEntityDataMapper;

  /**
   * Constructs a {@link StoriesRepository}.
   *
   * @param storiesDataStoreFactory A factory to construct different data source implementations.
   * @param storyEntityDataMapper {@link StoryEntityDataMapper}.
   * @param storyEntityDataMapper {@link StoryEntityDataMapper}.
   */
  @Inject StoriesDataRepository(StoriesDataStoreFactory storiesDataStoreFactory,
      StoryEntityDataMapper storyEntityDataMapper) {
    this.storiesDataStoreFactory = storiesDataStoreFactory;
    this.storyEntityDataMapper = storyEntityDataMapper;
  }

  @Override public Observable<List<Story>> stories() {
    final StoriesDataStore storiesDataStore = this.storiesDataStoreFactory.createCloudDataStore();

    return storiesDataStore.storiesEntityList()
        .concatMap(Observable::fromIterable)
        .take(10)
        .concatMap(
            integer -> storiesDataStore.storyEntity(integer).map(storyEntityDataMapper::transform))
        .toList()
        .toObservable();
  }

  @Override public Observable<List<StoryDetail>> storiesDetails(List<Integer> kids) {
    return Observable.fromIterable(kids).concatMap(this::storyDetails).toList().toObservable();
  }

  @Override public Observable<StoryDetail> storyDetails(Integer kid) {
    StoriesDataStore storiesDataStore = storiesDataStoreFactory.createCloudDataStore();

    return storiesDataStore.storyDetailEntity(kid)
        .concatMap(getStoryDetailEntityObservableFunction(storiesDataStore))
        .map(storyEntityDataMapper::transform);
  }

  @android.support.annotation.NonNull
  private Function<StoryDetailEntity, Observable<StoryDetailEntity>> getStoryDetailEntityObservableFunction(
      StoriesDataStore storiesDataStore) {
    return parentEntity -> {
      Observable parentObservable = Observable.just(parentEntity);

      if (parentEntity.getKids() != null) {
        Observable kidObserver = Observable.fromIterable(parentEntity.getKids())
            .concatMap(storiesDataStore::storyDetailEntity)
            .toList()
            .toObservable();

        return Observable.zip(kidObserver, parentObservable,
            (BiFunction<List<StoryDetailEntity>, StoryDetailEntity, StoryDetailEntity>) (storyDetailEntities, storyDetailEntity) -> {
              storyDetailEntity.setReplyList(storyDetailEntities);
              return storyDetailEntity;
            });
      } else {
        return parentObservable;
      }
    };
  }
}
