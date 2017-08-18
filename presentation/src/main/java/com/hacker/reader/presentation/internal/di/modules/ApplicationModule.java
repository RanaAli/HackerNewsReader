
package com.hacker.reader.presentation.internal.di.modules;

import android.content.Context;
import com.hacker.reader.data.executor.JobExecutor;
import com.hacker.reader.data.repository.StoriesDataRepository;
import com.hacker.reader.domain.executor.PostExecutionThread;
import com.hacker.reader.domain.executor.ThreadExecutor;
import com.hacker.reader.domain.repository.StoriesRepository;
import com.hacker.reader.presentation.AndroidApplication;
import com.hacker.reader.presentation.UIThread;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
  private final AndroidApplication application;

  public ApplicationModule(AndroidApplication application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides @Singleton PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides @Singleton StoriesRepository provideStoriesRepository(StoriesDataRepository storiesDataRepository) {
    return storiesDataRepository;
  }
}
