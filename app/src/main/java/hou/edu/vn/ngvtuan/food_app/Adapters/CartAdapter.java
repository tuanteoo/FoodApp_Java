package hou.edu.vn.ngvtuan.food_app.Adapters;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.CartModel;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private final List<CartModel> cartModelList;

    public CartAdapter(List<CartModel> cartModelList) {
        this.cartModelList = cartModelList;
    }


    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        CartModel cartModel = cartModelList.get(position);

        holder.image.setImageBitmap(BitmapFactory.decodeByteArray(cartModel.getImage(), 0, cartModel.getImage().length));
        holder.name.setText(cartModel.getName());
        holder.rating.setText(cartModel.getRating());
        holder.price.setText(String.valueOf(cartModel.getPrice()));
    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,rating,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.cart_img);
            name = itemView.findViewById(R.id.cart_name);
            rating = itemView.findViewById(R.id.cart_rating);
            price = itemView.findViewById(R.id.cart_price);
        }
    }
}
