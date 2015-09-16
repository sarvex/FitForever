package com.fitforever.utility.ailment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fitforever.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Sarvex Jatasra on 9/16/2015.
 */
public class AilmentAdapter extends RecyclerView.Adapter<AilmentAdapter.ViewHolder> {

  private final List<Ailment> ailments;

  public AilmentAdapter(List<Ailment> ailments) {
    this.ailments = ailments;
  }


  @Override
  public AilmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ailment, parent, false));

  }

  @Override
  public void onBindViewHolder(AilmentAdapter.ViewHolder holder, int position) {
    holder.bind(ailments.get(position));
  }

  @Override
  public int getItemCount() {
    return 0;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.ailment_photo) ImageView photo;
    @Bind(R.id.ailment_name) TextView name;
    @Bind(R.id.ailment_description) TextView description;

    public ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }

    public void bind(Ailment ailment) {
      photo.setImageResource(ailment.getImage());
      name.setText(ailment.getName());
      description.setText(ailment.getDescription());
    }
  }
}
