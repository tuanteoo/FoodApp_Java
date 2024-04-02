package hou.edu.vn.ngvtuan.food_app.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.CartModel;
import hou.edu.vn.ngvtuan.food_app.models.HomeVerModel;

public class HomeVerAdapter extends FirebaseRecyclerAdapter<HomeVerModel,HomeVerAdapter.ViewHolder> {
    BottomSheetDialog bottomSheetDialog;
    DatabaseReference databaseReference;
    public HomeVerAdapter(@NonNull FirebaseRecyclerOptions<HomeVerModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull HomeVerAdapter.ViewHolder holder, int position, @NonNull HomeVerModel model) {
        Glide.with(holder.itemView.getContext())
                .load(model.getImageFood())
                .into(holder.imageFood);
        holder.nameFood.setText(model.getNameFood());
        holder.timeCooking.setText(model.getTimeCooking());
        holder.priceFood.setText(String.valueOf(model.getPriceFood()));
        holder.quantitySold.setText(String.valueOf(model.getQuantitySold()));

        holder.imageFood.setOnClickListener(v -> {
            bottomSheetDialog = new BottomSheetDialog(holder.imageFood.getContext(),R.style.BottomSheetTheme);
            @SuppressLint("InflateParams")
            View sheetView = LayoutInflater.from(bottomSheetDialog.getContext()).inflate(R.layout.bottom_sheet,null);

            ImageView bottomImg = sheetView.findViewById(R.id.bottom_img);
            TextView bottomName = sheetView.findViewById(R.id.bottom_name);
            TextView bottomDesc = sheetView.findViewById(R.id.bottom_desc);
            TextView bottomTiming = sheetView.findViewById(R.id.bottom_timing);
            TextView bottomPrice = sheetView.findViewById(R.id.bottom_price);
            TextView bottomQuantitySold = sheetView.findViewById(R.id.bottom_quantitySold);
            TextView bottomQuantity = sheetView.findViewById(R.id.bottom_quantity);

            Glide.with(holder.itemView.getContext())
                    .load(model.getImageFood())
                    .into(bottomImg);
            bottomName.setText(model.getNameFood());
            bottomDesc.setText(model.getDescFood());
            bottomTiming.setText(model.getTimeCooking());
            bottomPrice.setText(String.valueOf(model.getPriceFood()));
            bottomQuantitySold.setText(String.valueOf(model.getQuantitySold()));

            //Firebase
            FirebaseAuth auth = FirebaseAuth.getInstance();
            String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Cart").child(userId);

            //Set default quantity = 1
            final int[] quantity = {1};
            //Get quantity Food Order
            bottomQuantity.setText(String.valueOf(quantity[0]));

            //Button Add quantity
            sheetView.findViewById(R.id.bottom_btnAdd).setOnClickListener(v2 ->{
                quantity[0]++;
                bottomQuantity.setText(String.valueOf(quantity[0]));
            });

            //Button Reduce quantity
            sheetView.findViewById(R.id.bottom_btnReduce).setOnClickListener(v3 ->{
                if (quantity[0] > 1) {
                    quantity[0]--;
                    bottomQuantity.setText(String.valueOf(quantity[0]));
                }
            });

            //Button Add Food To Cart
            sheetView.findViewById(R.id.bottom_btn_addtoCart).setOnClickListener(v1 -> {

                String Food_CartID = databaseReference.push().getKey();
                String imageFood = model.getImageFood();
                String nameFood = bottomName.getText().toString();
                int priceFood = Integer.parseInt(bottomPrice.getText().toString());
                int totalPriceFood = Integer.parseInt(bottomPrice.getText().toString()) * quantity[0];
                int quantityBuy = Integer.parseInt(bottomQuantity.getText().toString());

                databaseReference.child(Objects.requireNonNull(Food_CartID)).setValue(new CartModel(imageFood,nameFood,priceFood,totalPriceFood,quantityBuy));

                bottomSheetDialog.dismiss();
            });
            bottomSheetDialog.setContentView(sheetView);
            bottomSheetDialog.show();
        });
    }
    @NonNull
    @Override
    public HomeVerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_vertical_item,parent,false);

        return new ViewHolder(view);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageFood;
        TextView nameFood,timeCooking,quantitySold,priceFood;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageFood = itemView.findViewById(R.id.item_ver_image);
            nameFood = itemView.findViewById(R.id.item_ver_nameFood);
            timeCooking = itemView.findViewById(R.id.item_ver_timeCooking);
            quantitySold= itemView.findViewById(R.id.item_ver_quantitySold);
            priceFood = itemView.findViewById(R.id.item_ver_priceFood);
        }
    }
}
