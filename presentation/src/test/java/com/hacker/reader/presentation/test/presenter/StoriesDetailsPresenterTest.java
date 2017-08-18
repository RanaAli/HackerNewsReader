
package com.hacker.reader.presentation.test.presenter;

import android.content.Context;

import com.hacker.reader.domain.interactor.GetStoryDetail;
import com.hacker.reader.presentation.mapper.StoriesModelDataMapper;
import com.hacker.reader.presentation.presenter.StoriesDetailsPresenter;
import com.hacker.reader.presentation.view.StoriesDetailsView;

import io.reactivex.observers.DisposableObserver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StoriesDetailsPresenterTest {

    private StoriesDetailsPresenter storiesDetailsPresenter;

    List<Integer> testDataKids = new ArrayList<>(Arrays.asList(
            15043354,
            15043106,
            15041986,
            15040373,
            15042851,
            15043380,
            15041620,
            15041712,
            15043432,
            15040525,
            15039587,
            15041676,
            15045066));

    @Mock
    private Context mockContext;
    @Mock
    private StoriesDetailsView mockStoriesDetailsView;
    @Mock
    private GetStoryDetail mockGetStoryDetail;
    @Mock
    private StoriesModelDataMapper mockStoriesModelDataMapper;

    @Before
    public void setUp() {
        storiesDetailsPresenter = new StoriesDetailsPresenter(mockGetStoryDetail,
                mockStoriesModelDataMapper);
        storiesDetailsPresenter.setView(mockStoriesDetailsView);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testStoriesDetailsPresenterInitialize() {
        given(mockStoriesDetailsView.context()).willReturn(mockContext);

        storiesDetailsPresenter.initialize(testDataKids);

        verify(mockStoriesDetailsView).hideRetry();
        verify(mockStoriesDetailsView).showLoading();
        verify(mockGetStoryDetail).execute(any(DisposableObserver.class),
                any(GetStoryDetail.Params.class));
    }
}
