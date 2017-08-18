
package com.hacker.reader.presentation.test.view.activity;

import android.app.Fragment;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import com.hacker.reader.presentation.R;
import com.hacker.reader.presentation.view.activity.StoriesListActivity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class StoriesListActivityTest extends ActivityInstrumentationTestCase2<StoriesListActivity> {

  public static final String NEWS_LIST = "News List";
  private StoriesListActivity storiesListActivity;

  public StoriesListActivityTest() {
    super(StoriesListActivity.class);
  }

  @Override protected void setUp() throws Exception {
    super.setUp();
    this.setActivityIntent(createTargetIntent());
    storiesListActivity = getActivity();
  }

  @Override protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testContainsStoriesListFragment() {
    Fragment storiesListFragment =
        storiesListActivity.getFragmentManager().findFragmentById(R.id.fragmentContainer);
    assertThat(storiesListFragment, is(notNullValue()));
  }

  public void testContainsProperTitle() {
    String actualTitle = this.storiesListActivity.getTitle().toString().trim();

    assertThat(actualTitle, is(NEWS_LIST));
  }

  private Intent createTargetIntent() {
    Intent intentLaunchActivity =
        StoriesListActivity.getCallingIntent(getInstrumentation().getTargetContext());

    return intentLaunchActivity;
  }
}
