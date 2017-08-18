package com.hacker.reader.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.hacker.reader.presentation.R;
import com.hacker.reader.presentation.internal.di.HasComponent;
import com.hacker.reader.presentation.internal.di.components.DaggerStoryComponent;
import com.hacker.reader.presentation.internal.di.components.StoryComponent;
import com.hacker.reader.presentation.view.fragment.StoriesDetailsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity that shows details of a certain story.
 */
public class StoriesDetailsActivity extends BaseActivity implements HasComponent<StoryComponent> {

    private static final String INTENT_EXTRA_PARAM_STORY_KIDS =
            "com.hacker.reader.INTENT_PARAM_STORIES_KIDS";
    private static final String INSTANCE_STATE_PARAM_STORY_KIDS =
            "com.hacker.reader.STATE_PARAM_STORIES_KIDS";

    public static Intent getCallingIntent(Context context, List<Integer> kids) {
        Intent callingIntent = new Intent(context, StoriesDetailsActivity.class);
        callingIntent.putIntegerArrayListExtra(INTENT_EXTRA_PARAM_STORY_KIDS, new ArrayList<>(kids));
        return callingIntent;
    }

    private List<Integer> kids;
    private StoryComponent storyComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_layout);

        this.initializeActivity(savedInstanceState);
        this.initializeInjector();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putIntegerArrayList(INSTANCE_STATE_PARAM_STORY_KIDS, new ArrayList<>(this.kids));
        }
        super.onSaveInstanceState(outState);
    }

    /**
     * Initializes this activity.
     */
    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.kids = getIntent().getIntegerArrayListExtra(INTENT_EXTRA_PARAM_STORY_KIDS);
            if (this.kids == null) {
                kids = new ArrayList<>();
            }
            addFragment(R.id.fragmentContainer, StoriesDetailsFragment.forStory(kids));
        } else {
            this.kids = savedInstanceState.getIntegerArrayList(INSTANCE_STATE_PARAM_STORY_KIDS);
        }
    }

    private void initializeInjector() {
        this.storyComponent = DaggerStoryComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public StoryComponent getComponent() {
        return storyComponent;
    }
}
