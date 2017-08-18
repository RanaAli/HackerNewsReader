package com.hacker.reader.presentation.presenter;

import android.support.annotation.NonNull;
import com.hacker.reader.domain.Story;
import com.hacker.reader.domain.exception.DefaultErrorBundle;
import com.hacker.reader.domain.exception.ErrorBundle;
import com.hacker.reader.domain.interactor.DefaultObserver;
import com.hacker.reader.domain.interactor.GetStoriesList;
import com.hacker.reader.presentation.exception.ErrorMessageFactory;
import com.hacker.reader.presentation.internal.di.PerActivity;
import com.hacker.reader.presentation.mapper.StoriesModelDataMapper;
import com.hacker.reader.presentation.model.StoryModel;
import com.hacker.reader.presentation.view.StoriesListView;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity public class StoriesListPresenter implements Presenter {

  private StoriesListView viewListView;

  private final GetStoriesList getStoriesList;
  private final StoriesModelDataMapper storiesModelDataMapper;

  @Inject public StoriesListPresenter(GetStoriesList getStoriesList,
      StoriesModelDataMapper storiesModelDataMapper) {

    this.getStoriesList = getStoriesList;
    this.storiesModelDataMapper = storiesModelDataMapper;
  }

  public void setView(@NonNull StoriesListView view) {
    this.viewListView = view;
  }

  @Override public void resume() {
  }

  @Override public void pause() {
  }

  @Override public void destroy() {
    this.getStoriesList.dispose();
    this.viewListView = null;
  }

  /**
   * Initializes the presenter by start retrieving the list.
   */
  public void initialize() {
    this.loadStoriesList();
  }

  /**
   * Loads all Stories.
   */
  private void loadStoriesList() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getStoriesList();
  }

  private void getStoriesList() {
    this.getStoriesList.execute(new StoriesListObserver(), null);
  }

  public void onStoryClicked(List<Integer> kids) {
    this.viewListView.viewStory(kids);
  }

  private void showViewLoading() {
    this.viewListView.showLoading();
  }

  private void hideViewLoading() {
    this.viewListView.hideLoading();
  }

  private void showViewRetry() {
    this.viewListView.showRetry();
  }

  private void hideViewRetry() {
    this.viewListView.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage =
        ErrorMessageFactory.create(this.viewListView.context(), errorBundle.getException());
    this.viewListView.showError(errorMessage);
  }

  private void showStoriesCollectionInView(List<Story> stories) {
    final Collection<StoryModel> storiesModelCollection =
        this.storiesModelDataMapper.transform(stories);
    this.viewListView.renderStoriesList(storiesModelCollection);
  }

  private final class StoriesListObserver extends DefaultObserver<List<Story>> {
    @Override public void onNext(List<Story> stories) {
      StoriesListPresenter.this.showStoriesCollectionInView(stories);
    }

    @Override public void onComplete() {
      StoriesListPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable exception) {
      StoriesListPresenter.this.hideViewLoading();
      StoriesListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) exception));
      StoriesListPresenter.this.showViewRetry();
    }
  }
}
