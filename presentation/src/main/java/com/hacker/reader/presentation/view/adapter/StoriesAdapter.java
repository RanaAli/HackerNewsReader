package com.hacker.reader.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.hacker.reader.presentation.R;
import com.hacker.reader.presentation.model.StoryModel;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * Adaptar that manages a collection of {@link StoryModel}.
 */
public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.StoryViewHolder> {

  public interface OnItemClickListener {
    void onStoryItemClicked(List<Integer> kids);
  }

  private List<StoryModel> storyCollection;
  private final LayoutInflater layoutInflater;

  private OnItemClickListener onItemClickListener;

  @Inject StoriesAdapter(Context context) {
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.storyCollection = Collections.emptyList();
  }

  @Override public int getItemCount() {
    return (this.storyCollection != null) ? this.storyCollection.size() : 0;
  }

  @Override public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.row_story, parent, false);
    return new StoryViewHolder(view);
  }

  @Override public void onBindViewHolder(StoryViewHolder holder, final int position) {
    final StoryModel storyModel = this.storyCollection.get(position);
    holder.textViewTitle.setText(storyModel.getTitle());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (StoriesAdapter.this.onItemClickListener != null) {
          StoriesAdapter.this.onItemClickListener.onStoryItemClicked(storyModel.getKids());
        }
      }
    });
  }

  @Override public long getItemId(int position) {
    return position;
  }

  public void setStoryCollection(Collection<StoryModel> storyCollection) {
    this.validateStoryCollection(storyCollection);
    this.storyCollection = (List<StoryModel>) storyCollection;
    this.notifyDataSetChanged();
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  private void validateStoryCollection(Collection<StoryModel> storyCollection) {
    if (storyCollection == null) {
      throw new IllegalArgumentException("The list cannot be null");
    }
  }

  static class StoryViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.title) TextView textViewTitle;

    StoryViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
