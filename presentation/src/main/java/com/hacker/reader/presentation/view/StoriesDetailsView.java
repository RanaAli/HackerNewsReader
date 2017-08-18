
package com.hacker.reader.presentation.view;

import com.hacker.reader.presentation.model.StoryDetailModel;
import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a Stories profile.
 */
public interface StoriesDetailsView extends LoadDataView {
  /**
   * Render a Stories in the UI.
   *
   * @param storyDetailModels The list of {@link StoryDetailModel} that will be shown.
   */
  void renderStoryDetailList(Collection<StoryDetailModel> storyDetailModels);
}
