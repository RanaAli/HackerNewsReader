package com.hacker.reader.presentation.view.fragment;

import android.app.Activity;
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
import com.hacker.reader.presentation.model.StoryModel;
import com.hacker.reader.presentation.presenter.StoriesListPresenter;
import com.hacker.reader.presentation.view.StoriesListView;
import com.hacker.reader.presentation.view.adapter.StoriesAdapter;
import com.hacker.reader.presentation.view.adapter.StoriesLayoutManager;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

/**
 * Fragment that shows a list of Stories
 */
public class StoriesListFragment extends BaseFragment implements StoriesListView {

  /**
   * Interface for listening story list events.
   */
  public interface ListListener {
    void onStoryClicked(final List<Integer> kids);
  }

  @Inject StoriesListPresenter storiesListPresenter;
  @Inject StoriesAdapter storiesAdapter;

  @Bind(R.id.rv_stories) RecyclerView rv_stories;
  @Bind(R.id.rl_progress) RelativeLayout rl_progress;
  @Bind(R.id.rl_retry) RelativeLayout rl_retry;
  @Bind(R.id.bt_retry) Button bt_retry;

  private ListListener listListener;

  public StoriesListFragment() {
    setRetainInstance(true);
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof ListListener) {
      this.listListener = (ListListener) activity;
    }
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
    this.storiesListPresenter.setView(this);
    if (savedInstanceState == null) {
      this.loadStoriesList();
    }
  }

  @Override public void onResume() {
    super.onResume();
    this.storiesListPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.storiesListPresenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    rv_stories.setAdapter(null);
    ButterKnife.unbind(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.storiesListPresenter.destroy();
  }

  @Override public void onDetach() {
    super.onDetach();
    this.listListener = null;
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

  @Override public void viewStory(List<Integer> kids) {
    if (this.listListener != null) {
      this.listListener.onStoryClicked(kids);
    }
  }

  @Override public void renderStoriesList(Collection<StoryModel> storyModelCollection) {
    if (storyModelCollection != null) {
      this.storiesAdapter.setStoryCollection(storyModelCollection);
    }
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context context() {
    return this.getActivity().getApplicationContext();
  }

  private void setupRecyclerView() {
    this.storiesAdapter.setOnItemClickListener(onItemClickListener);
    this.rv_stories.setLayoutManager(new StoriesLayoutManager(context()));
    this.rv_stories.setAdapter(storiesAdapter);
  }

  /**
   * Loads all Stories.
   */
  private void loadStoriesList() {
    this.storiesListPresenter.initialize();
  }

  @OnClick(R.id.bt_retry) void onButtonRetryClick() {
    StoriesListFragment.this.loadStoriesList();
  }

  private StoriesAdapter.OnItemClickListener onItemClickListener =
      new StoriesAdapter.OnItemClickListener() {
        @Override public void onStoryItemClicked(List<Integer> kids) {
          if (StoriesListFragment.this.storiesListPresenter != null && kids != null && !kids.isEmpty()) {
            StoriesListFragment.this.storiesListPresenter.onStoryClicked(kids);
          }
        }
      };
}
