package hou.edu.vn.ngvtuan.food_app.ui.dailymeal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hou.edu.vn.ngvtuan.food_app.Adapters.DailyMealAdapter;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.DailyMealModel;

public class DailyMealFragment extends Fragment {

    RecyclerView recyclerView;
    List<DailyMealModel> dailyMealModels;
    DailyMealAdapter dailyMealAdapter;

    @SuppressLint("NotifyDataSetChanged")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dailymeal,container,false);

        recyclerView = root.findViewById(R.id.dailymeal_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dailyMealModels = new ArrayList<>();

        dailyMealModels.add(new DailyMealModel(R.drawable.breakfast,"Bữa Sáng","10% OFF","Miêu tả","Breakfast"));
        dailyMealModels.add(new DailyMealModel(R.drawable.lunch,"Bữa Trưa","20% OFF","Miêu tả","Lunch"));
        dailyMealModels.add(new DailyMealModel(R.drawable.dinner,"Bữa Tối","25% OFF","Miêu tả","Dinner"));
        dailyMealModels.add(new DailyMealModel(R.drawable.sweets,"Kẹo","15% OFF","Miêu tả","Sweets"));
        dailyMealModels.add(new DailyMealModel(R.drawable.coffe,"Cà Phê","5% OFF","Miêu tả","Coffee"));

        dailyMealAdapter = new DailyMealAdapter(getContext(),dailyMealModels);
        recyclerView.setAdapter(dailyMealAdapter);
        dailyMealAdapter.notifyDataSetChanged();

        return root;
    }


}