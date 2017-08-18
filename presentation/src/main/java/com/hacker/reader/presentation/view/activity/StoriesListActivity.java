
package com.hacker.reader.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import com.hacker.reader.presentation.R;
import com.hacker.reader.presentation.internal.di.HasComponent;
import com.hacker.reader.presentation.internal.di.components.DaggerStoryComponent;
import com.hacker.reader.presentation.internal.di.components.StoryComponent;
import com.hacker.reader.presentation.view.fragment.StoriesListFragment;
import java.util.List;

/**
 * Activity that shows a list of Stories.
 */
public class StoriesListActivity extends BaseActivity implements HasComponent<StoryComponent>,
    StoriesListFragment.ListListener {

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, StoriesListActivity.class);
  }

  private StoryComponent storyComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_layout);

    this.initializeInjector();
    if (savedInstanceState == null) {
      addFragment(R.id.fragmentContainer, new StoriesListFragment());
    }
  }

  private void initializeInjector() {
    this.storyComponent = DaggerStoryComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public StoryComponent getComponent() {
    return storyComponent;
  }

  @Override public void onStoryClicked(List<Integer> kids) {
    this.navigator.navigateToStoryDetails(this, kids);
  }
}
