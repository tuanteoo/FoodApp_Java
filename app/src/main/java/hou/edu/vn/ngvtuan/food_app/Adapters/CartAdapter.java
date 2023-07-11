package hou.edu.vn.ngvtuan.food_app.Adapters;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hou.edu.vn.ngvtuan.food_app.DataBase.DataBaseHandler;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.CartModel;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private final ArrayList<CartModel> cartModelList;
    private OnItemDeletedListener onItemDeletedListener;

    public CartAdapter(ArrayList<CartModel> cartModelList) {
        this.cartModelList = cartModelList;
    }
    public interface OnItemDeletedListener {
        void onItemDeleted();
    }
    public void setOnItemDeletedListener(OnItemDeletedListener onItemDeletedListener) {
        this.onItemDeletedListener = onItemDeletedListener;
    }
    private DataBaseHandler dataBaseHandler;


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

        holder.btnDeleteFood.setOnClickListener(v -> {
            dataBaseHandler = new DataBaseHandler(v.getContext());
            dataBaseHandler.DeleteFoodByName(cartModel.getName());

            cartModelList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartModelList.size());

            if (onItemDeletedListener != null) {
                onItemDeletedListener.onItemDeleted();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,rating,price;
        Button btnDeleteFood;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.cart_img);
            name = itemView.findViewById(R.id.cart_name);
            rating = itemView.findViewById(R.id.cart_rating);
            price = itemView.findViewById(R.id.cart_price);
            btnDeleteFood = itemView.findViewById(R.id.deleteFood);
        }
    }
}
