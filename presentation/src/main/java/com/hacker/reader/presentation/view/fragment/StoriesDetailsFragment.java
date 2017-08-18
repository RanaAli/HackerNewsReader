package com.hacker.reader.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hacker.reader.presentation.R;
import com.hacker.reader.presentation.internal.di.components.StoryComponent;
import com.hacker.reader.presentation.model.StoryDetailModel;
import com.hacker.reader.presentation.presenter.StoriesDetailsPresenter;
import com.hacker.reader.presentation.view.StoriesDetailsView;
import com.hacker.reader.presentation.view.adapter.StoryDetailsAdapter;
import com.hacker.reader.presentation.view.adapter.StoriesLayoutManager;
import dagger.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

/**
 * Fragment that shows details of a certain Stories.
 */
public class StoriesDetailsFragment extends BaseFragment implements StoriesDetailsView {
  private static final String PARAM_KIDS = "param_kids";

  @Inject StoriesDetailsPresenter storiesDetailsPresenter;
  @Inject StoryDetailsAdapter storyDetailsAdapter;

  @Bind(R.id.rv_stories) RecyclerView rv_stories;
  @Bind(R.id.rl_progress) RelativeLayout rl_progress;
  @Bind(R.id.rl_retry) RelativeLayout rl_retry;
  @Bind(R.id.bt_retry) Button bt_retry;

  public static StoriesDetailsFragment forStory(List<Integer> kids) {
    final StoriesDetailsFragment StoriesDetailsFragment = new StoriesDetailsFragment();
    final Bundle arguments = new Bundle();

    arguments.putIntegerArrayList(PARAM_KIDS, new ArrayList<>(kids));
    StoriesDetailsFragment.setArguments(arguments);

    return StoriesDetailsFragment;
  }

  public StoriesDetailsFragment() {
    setRetainInstance(true);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(StoryComponent.class).inject(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_stories_list, container, false);
    ButterKnife.bind(this, fragmentView);
    setupRecyclerView();
    return fragmentView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.storiesDetailsPresenter.setView(this);
    if (savedInstanceState == null) {
      this.loadStoryDetails();
    }
  }

  @Override public void onResume() {
    super.onResume();
    this.storiesDetailsPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.storiesDetailsPresenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.storiesDetailsPresenter.destroy();
  }

  @Override public void renderStoryDetailList(Collection<StoryDetailModel> storyDetailModels) {
    if (storyDetailModels != null) {
      this.storyDetailsAdapter.setStoryDetailModels(storyDetailModels);
    }
  }

  @Override public void showLoading() {
    this.rl_progress.setVisibility(View.VISIBLE);
    this.getActivity().setProgressBarIndeterminateVisibility(true);
  }

  @Override public void hideLoading() {
    this.rl_progress.setVisibility(View.GONE);
    this.getActivity().setProgressBarIndeterminateVisibility(false);
  }

  @Override public void showRetry() {
    this.rl_retry.setVisibility(View.VISIBLE);
  }

  @Override public void hideRetry() {
    this.rl_retry.setVisibility(View.GONE);
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context context() {
    return getActivity().getApplicationContext();
  }

  private void setupRecyclerView() {
    this.rv_stories.setLayoutManager(new StoriesLayoutManager(context()));
    this.rv_stories.setAdapter(storyDetailsAdapter);
  }

  /**
   * Load Stories details.
   */
  private void loadStoryDetails() {
    if (this.storiesDetailsPresenter != null) {
      this.storiesDetailsPresenter.initialize(currentKids());
    }
  }

  /**
   * Get current kids from fragments arguments.
   */
  private List<Integer> currentKids() {
    final Bundle arguments = getArguments();
    Preconditions.checkNotNull(arguments, "Fragment arguments cannot be null");
    return arguments.getIntegerArrayList(PARAM_KIDS);
  }

  @OnClick(R.id.bt_retry) void onButtonRetryClick() {
    StoriesDetailsFragment.this.loadStoryDetails();
  }
}
