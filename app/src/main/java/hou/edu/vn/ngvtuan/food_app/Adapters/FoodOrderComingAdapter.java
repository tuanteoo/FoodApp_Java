package hou.edu.vn.ngvtuan.food_app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.CartModel;

public class FoodOrderComingAdapter extends RecyclerView.Adapter<FoodOrderComingAdapter.ViewHolder> {

    private final ArrayList<CartModel> cartModels;

    public FoodOrderComingAdapter(ArrayList<CartModel> cartModels){
        this.cartModels = cartModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_ordercoming,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(cartModels.get(position).getImageFood())
                .into(holder.imageFood);
        holder.nameFood.setText(cartModels.get(position).getNameFood());
    }

    @Override
    public int getItemCount() {
        return cartModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageFood;
        TextView nameFood;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageFood = itemView.findViewById(R.id.item_coming_imageFood);
            nameFood = itemView.findViewById(R.id.item_coming_nameFood);
        }
    }
}
