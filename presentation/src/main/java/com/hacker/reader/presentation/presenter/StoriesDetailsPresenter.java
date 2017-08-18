package com.hacker.reader.presentation.presenter;

import android.support.annotation.NonNull;
import com.hacker.reader.domain.StoryDetail;
import com.hacker.reader.domain.exception.DefaultErrorBundle;
import com.hacker.reader.domain.exception.ErrorBundle;
import com.hacker.reader.domain.interactor.DefaultObserver;
import com.hacker.reader.domain.interactor.GetStoryDetail;
import com.hacker.reader.presentation.exception.ErrorMessageFactory;
import com.hacker.reader.presentation.internal.di.PerActivity;
import com.hacker.reader.presentation.mapper.StoriesModelDataMapper;
import com.hacker.reader.presentation.model.StoryDetailModel;
import com.hacker.reader.presentation.view.StoriesDetailsView;

import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity public class StoriesDetailsPresenter implements Presenter {

  private StoriesDetailsView viewDetailsView;

  private final GetStoryDetail getStoryDetailUseCase;
  private final StoriesModelDataMapper storiesModelDataMapper;

  @Inject public StoriesDetailsPresenter(GetStoryDetail getStoryDetailUseCase,
                                         StoriesModelDataMapper storiesModelDataMapper) {

    this.getStoryDetailUseCase = getStoryDetailUseCase;
    this.storiesModelDataMapper = storiesModelDataMapper;
  }

  public void setView(@NonNull StoriesDetailsView view) {
    this.viewDetailsView = view;
  }

  @Override public void resume() {
  }

  @Override public void pause() {
  }

  @Override public void destroy() {
    this.getStoryDetailUseCase.dispose();
    this.viewDetailsView = null;
  }

  /**
   * Initializes the presenter by showing/hiding proper views
   * and retrieving details.
   */
  public void initialize(List<Integer> kids) {
    this.hideViewRetry();
    this.showViewLoading();
    this.getStoryDetails(kids);
  }

  private void getStoryDetails(List<Integer> kids) {
    this.getStoryDetailUseCase.execute(new StoryDetailsObserver(),
        GetStoryDetail.Params.forStory(kids));
  }

  private void showViewLoading() {
    this.viewDetailsView.showLoading();
  }

  private void hideViewLoading() {
    this.viewDetailsView.hideLoading();
  }

  private void showViewRetry() {
    this.viewDetailsView.showRetry();
  }

  private void hideViewRetry() {
    this.viewDetailsView.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage =
        ErrorMessageFactory.create(this.viewDetailsView.context(), errorBundle.getException());
    this.viewDetailsView.showError(errorMessage);
  }

  private void showStoriesDetailsInView(List<StoryDetail> storyDetail) {
    final Collection<StoryDetailModel> storyDetailModels =
        this.storiesModelDataMapper.transformStoryDetails(storyDetail);
    this.viewDetailsView.renderStoryDetailList(storyDetailModels);
  }

  private final class StoryDetailsObserver extends DefaultObserver<List<StoryDetail>> {

    @Override public void onComplete() {
      StoriesDetailsPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      StoriesDetailsPresenter.this.hideViewLoading();
      StoriesDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      StoriesDetailsPresenter.this.showViewRetry();
    }

    @Override public void onNext(List<StoryDetail> storyDetailList) {
      StoriesDetailsPresenter.this.showStoriesDetailsInView(storyDetailList);
    }
  }
}
