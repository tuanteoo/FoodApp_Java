package hou.edu.vn.ngvtuan.food_app.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

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

    @Override
    public void onBindViewHolder(@NonNull HomeVerAdapter.ViewHolder holder, int position) {

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
            sheetView.findViewById(R.id.bottom_btn_addtoCart).setOnClickListener(v1 -> {
                // Call the InsertDataToOrder method here
                Toast.makeText(context, "Đã thêm vào giỏ hàng",Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            });

            ImageView bottomImg = sheetView.findViewById(R.id.bottom_img);
            TextView bottomName = sheetView.findViewById(R.id.bottom_name);
            TextView bottomRating = sheetView.findViewById(R.id.bottom_rating);
            TextView bottomTiming = sheetView.findViewById(R.id.bottom_timing);
            TextView bottomPrice = sheetView.findViewById(R.id.bottom_price);

            bottomImg.setImageResource(mImage);
            bottomName.setText(mName);
            bottomRating.setText(mRating);
            bottomTiming.setText(mTiming);
            bottomPrice.setText(mPrice);

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
