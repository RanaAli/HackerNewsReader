
package com.hacker.reader.presentation.view;

import com.hacker.reader.presentation.model.StoryModel;
import java.util.Collection;
import java.util.List;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link StoryModel}.
 */
public interface StoriesListView extends LoadDataView {
  /**
   * View a {@link StoryModel} profile/details.
   *
   * @param kids The story kids that will be shown.
   */
  void viewStory(List<Integer> kids);

  /**
   * Render a story list in the UI.
   *
   * @param storiesModelCollection The collection of {@link StoryModel} that will be shown.
   */
  void renderStoriesList(Collection<StoryModel> storiesModelCollection);
}
