
package com.hacker.reader.presentation.internal.di.components;

import android.content.Context;
import com.hacker.reader.domain.executor.PostExecutionThread;
import com.hacker.reader.domain.executor.ThreadExecutor;
import com.hacker.reader.domain.repository.StoriesRepository;
import com.hacker.reader.presentation.internal.di.modules.ApplicationModule;
import com.hacker.reader.presentation.view.activity.BaseActivity;
import dagger.Component;
import javax.inject.Singleton;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(BaseActivity baseActivity);

  //Exposed to sub-graphs.
  Context context();
  ThreadExecutor threadExecutor();
  PostExecutionThread postExecutionThread();
  StoriesRepository storiesRepository();
}
