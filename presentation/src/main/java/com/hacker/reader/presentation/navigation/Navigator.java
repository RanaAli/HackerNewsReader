package com.hacker.reader.presentation.navigation;

import android.content.Context;
import android.content.Intent;
import com.hacker.reader.presentation.view.activity.StoriesDetailsActivity;
import com.hacker.reader.presentation.view.activity.StoriesListActivity;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton public class Navigator {

  @Inject public Navigator() {
    //empty
  }

  /**
   * Goes to the stories list screen.
   *
   * @param context A Context needed to open the destiny activity.
   */
  public void navigateToStoriesList(Context context) {
    if (context != null) {
      Intent intentToLaunch = StoriesListActivity.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }

  /**
   * Goes to the details screen.
   *
   * @param context A Context needed to open the destiny activity.
   * @param kids list of comments
   */
  public void navigateToStoryDetails(Context context, List<Integer> kids) {
    if (context != null) {
      Intent intentToLaunch = StoriesDetailsActivity.getCallingIntent(context, kids);
      context.startActivity(intentToLaunch);
    }
  }
}
