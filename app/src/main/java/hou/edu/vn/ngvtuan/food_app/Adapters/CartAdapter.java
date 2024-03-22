package hou.edu.vn.ngvtuan.food_app.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.CartModel;

public class CartAdapter extends FirebaseRecyclerAdapter<CartModel,CartAdapter.ViewHolder> {
    public CartAdapter(@NonNull FirebaseRecyclerOptions<CartModel> options) {
        super(options);
    }
    DatabaseReference databaseReference;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position, @NonNull CartModel model) {
        Glide.with(holder.itemView.getContext())
                .load(model.getImageFood())
                .into(holder.image);
        holder.name.setText(model.getNameFood());
        holder.price.setText(String.valueOf(model.getPriceFood()));
        holder.price_quantity.setText(String.valueOf(model.getPrice_quantity()));
        holder.quantity.setText(String.valueOf(model.getQuantity()));

        //Firebase
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Cart").child(userId);


        holder.btnPLus.setOnClickListener(v -> {
            model.setQuantity(model.getQuantity() + 1);
            Update_QuantityBuy_Cart(position,model.getQuantity(),model.getPriceFood()*model.getQuantity(),holder);

        });

        holder.btnReduce.setOnClickListener(v -> {
            model.setQuantity(model.getQuantity() - 1);
            Update_QuantityBuy_Cart(position,model.getQuantity(),model.getPriceFood()*model.getQuantity(),holder);

            if (model.getQuantity() == 0){
                databaseReference.child(Objects.requireNonNull(getRef(position).getKey())).removeValue();
            }
        });

        holder.btnDeleteFood.setOnClickListener(v -> {
            databaseReference.child(Objects.requireNonNull(getRef(position).getKey())).removeValue();
        });
    }
    private void Update_QuantityBuy_Cart(int position,int quantity,int totalPriceFood,ViewHolder holder){
        Map<String,Object> map = new HashMap<>();

        map.put("quantity",quantity);
        map.put("price_quantity",totalPriceFood);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        FirebaseDatabase.getInstance().getReference().child("Cart").child(userId)
                .child(Objects.requireNonNull(getRef(position).getKey())).updateChildren(map);

        holder.quantity.setText(String.valueOf(quantity));
        holder.price_quantity.setText(String.valueOf(totalPriceFood));
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item,parent,false);

        return new CartAdapter.ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,price,price_quantity,quantity;
        Button btnDeleteFood;
        ImageButton btnPLus,btnReduce;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.cart_img);
            name = itemView.findViewById(R.id.cart_name);
            price_quantity = itemView.findViewById(R.id.cart_price_quantity);
            price = itemView.findViewById(R.id.cart_price);
            quantity = itemView.findViewById(R.id.cart_quantity);
            btnDeleteFood = itemView.findViewById(R.id.deleteFood);
            btnPLus = itemView.findViewById(R.id.cart_btnPlus);
            btnReduce = itemView.findViewById(R.id.cart_btnReduce);
        }
    }
}
