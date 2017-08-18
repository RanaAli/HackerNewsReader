package com.hacker.reader.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.hacker.reader.presentation.R;
import com.hacker.reader.presentation.model.StoryDetailModel;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * Adaptar that manages a collection of {@link StoryDetailModel}.
 */
public class StoryDetailsAdapter extends RecyclerView.Adapter<StoryDetailsAdapter.StoryViewHolder> {

  public interface OnItemClickListener {
    void onItemClicked(List<Integer> kids);
  }

  private List<StoryDetailModel> storyDetailModels;
  private final LayoutInflater layoutInflater;

  private OnItemClickListener onItemClickListener;

  @Inject StoryDetailsAdapter(Context context) {
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.storyDetailModels = Collections.emptyList();
  }

  @Override public int getItemCount() {
    return (this.storyDetailModels != null) ? this.storyDetailModels.size() : 0;
  }

  @Override public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.row_story_detail, parent, false);
    return new StoryViewHolder(view);
  }

  @Override public void onBindViewHolder(StoryViewHolder holder, final int position) {
    final StoryDetailModel storyModel = this.storyDetailModels.get(position);

    if (storyModel.getText() != null) {
      holder.textViewComment.setText(Html.fromHtml(storyModel.getText()));
    }

    if (storyModel.getReply() != null) {
      holder.textViewReply.setText(Html.fromHtml(storyModel.getReply()));
    } else {
      holder.textViewReply.setVisibility(View.GONE);
    }

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (StoryDetailsAdapter.this.onItemClickListener != null) {
          StoryDetailsAdapter.this.onItemClickListener.onItemClicked(storyModel.getKids());
        }
      }
    });
  }

  @Override public long getItemId(int position) {
    return position;
  }

  public void setStoryDetailModels(Collection<StoryDetailModel> storyDetailModels) {
    this.validateStoryCollection(storyDetailModels);
    this.storyDetailModels = (List<StoryDetailModel>) storyDetailModels;
    this.notifyDataSetChanged();
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  private void validateStoryCollection(Collection<StoryDetailModel> storyDetailCollection) {
    if (storyDetailCollection == null) {
      throw new IllegalArgumentException("The list cannot be null");
    }
  }

  static class StoryViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.tv_coment) TextView textViewComment;
    @Bind(R.id.tv_repy) TextView textViewReply;

    StoryViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
