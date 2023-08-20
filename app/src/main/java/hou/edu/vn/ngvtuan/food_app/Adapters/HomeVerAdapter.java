package hou.edu.vn.ngvtuan.food_app.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import hou.edu.vn.ngvtuan.food_app.DataBase.DataBaseHandler;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.HomeVerModel;

public class HomeVerAdapter extends RecyclerView.Adapter<HomeVerAdapter.ViewHolder> {
    private BottomSheetDialog bottomSheetDialog;
    Context context;
    ArrayList<HomeVerModel> list;
    private DataBaseHandler dataBaseHandler;
    public HomeVerAdapter(Context context, ArrayList<HomeVerModel> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public HomeVerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_vertical_item, parent, false));
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final int mImage = list.get(position).getImage();
        final String mName = list.get(position).getName();
        final String mTiming = list.get(position).getTiming();
        final String mPrice = list.get(position).getPrice();
        final String mRating = list.get(position).getRating();

        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.timing.setText(list.get(position).getTiming());
        holder.price.setText(list.get(position).getPrice());
        holder.rating.setText(list.get(position).getRating());

        holder.imageView.setOnClickListener(v -> {
            bottomSheetDialog = new BottomSheetDialog(context,R.style.BottomSheetTheme);
            @SuppressLint("InflateParams")
            View sheetView = LayoutInflater.from(context).inflate(R.layout.bottom_sheet,null);

            ImageView bottomImg = sheetView.findViewById(R.id.bottom_img);
            TextView bottomName = sheetView.findViewById(R.id.bottom_name);
            TextView bottomRating = sheetView.findViewById(R.id.bottom_rating);
            TextView bottomTiming = sheetView.findViewById(R.id.bottom_timing);
            TextView bottomPrice = sheetView.findViewById(R.id.bottom_price);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView bottomQuantity = sheetView.findViewById(R.id.bottom_quantity);

            bottomImg.setImageResource(mImage);
            bottomName.setText(mName);
            bottomRating.setText(mRating);
            bottomTiming.setText(mTiming);
            bottomPrice.setText(mPrice);

            //Set default quantity = 1
            final int[] quantity = {1};

            //Get quantity Food Order
            quantity[0] = Integer.parseInt(bottomQuantity.getText().toString());

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
                //Convert image to byte[]
                Resources res = context.getResources();
                Bitmap bitmap = BitmapFactory.decodeResource(res, mImage);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
                byte[] byteImage = stream.toByteArray();

                // Call the InsertDataToOrder method here
                dataBaseHandler = new DataBaseHandler(context);
                boolean check = dataBaseHandler.InsertDataToOrder(byteImage,mName,mRating, quantity[0],Integer.parseInt(mPrice));
                if (check){
                   Toast.makeText(context, "Đã thêm vào giỏ hàng",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Lỗi",Toast.LENGTH_SHORT).show();
                }
                bottomSheetDialog.dismiss();
            });
            bottomSheetDialog.setContentView(sheetView);
            bottomSheetDialog.show();
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,timing,rating,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ver_img);
            name = itemView.findViewById(R.id.name);
            timing = itemView.findViewById(R.id.timing);
            rating= itemView.findViewById(R.id.rating);
            price = itemView.findViewById(R.id.price);
        }
    }
}
