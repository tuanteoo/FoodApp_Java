package hou.edu.vn.ngvtuan.food_app.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import hou.edu.vn.ngvtuan.food_app.Adapters.DetailedDailyAdapter;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.databinding.ActivityDetaledDailyMealBinding;
import hou.edu.vn.ngvtuan.food_app.models.DetailedDailyModel;

public class DetailedDailyMealActivity extends AppCompatActivity {

    ActivityDetaledDailyMealBinding binding;
    List<DetailedDailyModel> detailedDailyModelList;
    DetailedDailyAdapter dailyAdapter;


    @SuppressLint({"NotifyDataSetChanged", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetaledDailyMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type = getIntent().getStringExtra("type");

        binding.detailedRec.setLayoutManager(new LinearLayoutManager(this));
        detailedDailyModelList = new ArrayList<>();
        dailyAdapter = new DetailedDailyAdapter(detailedDailyModelList);
        binding.detailedRec.setAdapter(dailyAdapter);

        //Fab_MovetoCart
        binding.btnMovetoCart.setOnClickListener(v -> {

            //move to My Cart
            Intent intent = new Intent(DetailedDailyMealActivity.this, LayoutMyCartActivity.class);
            startActivity(intent);
        });

        //Add Food BreakFast
        if (type != null && type.equalsIgnoreCase("Breakfast")){
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.fav1,"Bữa Sáng","Miêu tả","4.4","40","06:00 - 14:00"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.fav2,"Bữa Sáng","Miêu tả","4.8","50","08:00 - 23:00"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.fav3,"Bữa Sáng","Miêu tả","4.9","65","07:00 - 13:00"));
            dailyAdapter.notifyDataSetChanged();
        }

        //Add Food Sweets
        if (type != null && type.equalsIgnoreCase("Sweets")) {
            binding.detailedImage.setImageResource(R.drawable.sweets);
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s1, "Kẹo", "Miêu tả", "4.5", "10", "08:00 - 17:00"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s2, "Kẹo", "Miêu tả", "5.0", "15", "07:30 - 23:00"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s3, "Kẹo", "Miêu tả", "4.7", "20", "09:00 - 22:00"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s4, "Kẹo", "Miêu tả", "4.8", "25", "08:30 - 21:00"));
            dailyAdapter.notifyDataSetChanged();
        }
    }
}