
package com.hacker.reader.presentation.test.presenter;

import android.content.Context;
import com.hacker.reader.domain.interactor.GetStoriesList;
import com.hacker.reader.presentation.mapper.StoriesModelDataMapper;
import com.hacker.reader.presentation.presenter.StoriesListPresenter;
import com.hacker.reader.presentation.view.StoriesListView;
import io.reactivex.observers.DisposableObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StoriesListPresenterTest {

    private StoriesListPresenter storiesListPresenter;

    @Mock
    private Context mockContext;
    @Mock
    private StoriesListView mockStoriesListView;
    @Mock
    private GetStoriesList mockGetStoriesList;
    @Mock
    private StoriesModelDataMapper mockStoriesModelDataMapper;

    @Before
    public void setUp() {
        storiesListPresenter =
                new StoriesListPresenter(mockGetStoriesList, mockStoriesModelDataMapper);
        storiesListPresenter.setView(mockStoriesListView);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testStoriesListPresenterInitialize() {
        given(mockStoriesListView.context()).willReturn(mockContext);

        storiesListPresenter.initialize();

        verify(mockStoriesListView).hideRetry();
        verify(mockStoriesListView).showLoading();
        verify(mockGetStoriesList).execute(any(DisposableObserver.class), any(Void.class));
    }
}
