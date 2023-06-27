package hou.edu.vn.ngvtuan.food_app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.FeaturedModel;
import hou.edu.vn.ngvtuan.food_app.models.FeaturedVerModel;

public class FeaturedVerAdapter extends RecyclerView.Adapter<FeaturedVerAdapter.ViewHolder> {

    List<FeaturedVerModel> list;

    public FeaturedVerAdapter(List<FeaturedVerModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public FeaturedVerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_ver_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedVerAdapter.ViewHolder holder, int position) {
        holder.image.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.desc.setText(list.get(position).getDesc());
        holder.rating.setText(list.get(position).getRating());
        holder.timing.setText(list.get(position).getTiming());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,desc,rating,timing;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.featured_ver_img);
            name = itemView.findViewById(R.id.featured_ver_name);
            desc = itemView.findViewById(R.id.featured_ver_desc);
            rating = itemView.findViewById(R.id.featured_ver_rating);
            timing = itemView.findViewById(R.id.featured_ver_timing);
        }
    }
}
