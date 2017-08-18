
package com.hacker.reader.presentation.internal.di.components;

import com.hacker.reader.presentation.internal.di.PerActivity;
import com.hacker.reader.presentation.internal.di.modules.ActivityModule;
import com.hacker.reader.presentation.view.fragment.StoriesDetailsFragment;
import com.hacker.reader.presentation.view.fragment.StoriesListFragment;
import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects Stories specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface StoryComponent extends ActivityComponent {
    void inject(StoriesListFragment storiesListFragment);

    void inject(StoriesDetailsFragment storiesDetailsFragment);
}
