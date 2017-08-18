package com.hacker.reader.presentation.test.view.activity;

import android.app.Fragment;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import com.hacker.reader.presentation.R;
import com.hacker.reader.presentation.view.activity.StoriesDetailsActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class StoriesDetailsActivityTest
    extends ActivityInstrumentationTestCase2<StoriesDetailsActivity> {

  public static final String TITLE = "News Details";
  private StoriesDetailsActivity storiesDetailsActivity;

  List<Integer> kids = new ArrayList<>(
      Arrays.asList(15043354, 15043106, 15041986, 15040373, 15042851, 15043380, 15041620, 15041712,
          15043432, 15040525, 15039587, 15041676, 15045066));

  public StoriesDetailsActivityTest() {
    super(StoriesDetailsActivity.class);
  }

  @Override protected void setUp() throws Exception {
    super.setUp();
    this.setActivityIntent(createTargetIntent());
    this.storiesDetailsActivity = getActivity();
  }

  @Override protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testContainsDetailsFragment() {
    Fragment storiesDetailsFragment =
        storiesDetailsActivity.getFragmentManager().findFragmentById(R.id.fragmentContainer);
    assertThat(storiesDetailsFragment, is(notNullValue()));
  }

  public void testContainsProperTitle() {
    String actualTitle = this.storiesDetailsActivity.getTitle().toString().trim();

    assertThat(actualTitle, is(TITLE));
  }

  public void testLoadViews() {
    onView(withId(R.id.rv_stories)).check(matches(isDisplayed()));

    onView(withId(R.id.rl_progress)).check(matches(isDisplayed()));
    onView(withId(R.id.rl_retry)).check(matches(not(isDisplayed())));
    onView(withId(R.id.bt_retry)).check(matches(not(isDisplayed())));
  }

  private Intent createTargetIntent() {
    Intent intentLaunchActivity =
        StoriesDetailsActivity.getCallingIntent(getInstrumentation().getTargetContext(), kids);

    return intentLaunchActivity;
  }
}
