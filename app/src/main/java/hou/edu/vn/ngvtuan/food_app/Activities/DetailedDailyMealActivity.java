package hou.edu.vn.ngvtuan.food_app.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import hou.edu.vn.ngvtuan.food_app.Adapters.DetailedDailyAdapter;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.DetailedDailyModel;

public class DetailedDailyMealActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<DetailedDailyModel> detailedDailyModelList;
    DetailedDailyAdapter dailyAdapter;
    ImageView imageView;
    FloatingActionButton btnMove_toCart;

    @SuppressLint({"NotifyDataSetChanged", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaled_daily_meal);

        String type = getIntent().getStringExtra("type");

        recyclerView = findViewById(R.id.detailed_rec);
        imageView = findViewById(R.id.detailed_image);
        btnMove_toCart = (FloatingActionButton) findViewById(R.id.btn_movetoCart);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailedDailyModelList = new ArrayList<>();
        dailyAdapter = new DetailedDailyAdapter(detailedDailyModelList);
        recyclerView.setAdapter(dailyAdapter);
        btnMove_toCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                @SuppressLint("CommitTransaction") FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.add(R.id.mycart, new MyCartFragment()).commit();

            }
        });

        if (type != null && type.equalsIgnoreCase("Breakfast")){
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.fav1,"Bữa Sáng","Miêu tả","4.4","40","06:00 - 14:00"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.fav2,"Bữa Sáng","Miêu tả","4.8","50","08:00 - 23:00"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.fav3,"Bữa Sáng","Miêu tả","4.9","65","07:00 - 13:00"));
            dailyAdapter.notifyDataSetChanged();
        }

        if (type != null && type.equalsIgnoreCase("Sweets")) {
            imageView.setImageResource(R.drawable.sweets);
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s1, "Kẹo", "Miêu tả", "4.5", "10", "08:00 - 17:00"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s2, "Kẹo", "Miêu tả", "5.0", "15", "07:30 - 23:00"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s3, "Kẹo", "Miêu tả", "4.7", "20", "09:00 - 22:00"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s4, "Kẹo", "Miêu tả", "4.8", "25", "08:30 - 21:00"));
            dailyAdapter.notifyDataSetChanged();
        }

    }

}