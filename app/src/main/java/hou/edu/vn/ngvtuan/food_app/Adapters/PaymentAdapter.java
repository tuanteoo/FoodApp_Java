package hou.edu.vn.ngvtuan.food_app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.CartModel;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder> {
    List<CartModel> cartModels;

    public PaymentAdapter(List<CartModel> cartModels){
        this.cartModels = cartModels;
    }

    @NonNull
    @Override
    public PaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_payment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdapter.ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(cartModels.get(position).getImageFood())
                .into(holder.imageFood);
        holder.nameFood.setText(cartModels.get(position).getNameFood());
        holder.quantity.setText(String.valueOf(cartModels.get(position).getQuantity()));
        holder.totalPrice.setText(String.valueOf(cartModels.get(position).getPrice_quantity()));
    }

    @Override
    public int getItemCount() {
        return cartModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageFood;
        TextView quantity,totalPrice,nameFood;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageFood = itemView.findViewById(R.id.item_payment_imageFood);
            nameFood = itemView.findViewById(R.id.item_payment_nameFood);
            quantity = itemView.findViewById(R.id.quantity_foodOrder);
            totalPrice = itemView.findViewById(R.id.totalprice_foodorder);
        }
    }
}
