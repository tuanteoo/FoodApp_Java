package hou.edu.vn.ngvtuan.food_app.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hou.edu.vn.ngvtuan.food_app.Activities.UserInfoActivity;
import hou.edu.vn.ngvtuan.food_app.Adapters.HomeHorAdapter;
import hou.edu.vn.ngvtuan.food_app.Adapters.HomeVerAdapter;
import hou.edu.vn.ngvtuan.food_app.Adapters.UpdateVerticalRec;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.HomeHorModel;
import hou.edu.vn.ngvtuan.food_app.models.HomeVerModel;


public class HomeFragment extends Fragment implements UpdateVerticalRec {
    RecyclerView homeHorizontalRec,homeVerticalRec;
    ArrayList<HomeHorModel> homeHorModelList;
    HomeHorAdapter homeHorAdapter;
    ArrayList<HomeVerModel> homeVerModelList;
    HomeVerAdapter homeVerAdapter;
    ImageButton imageButton;


    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home,container,false);

        imageButton = root.findViewById(R.id.img_btn_UserInfo);
        imageButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UserInfoActivity.class);
            startActivity(intent);
        });

        homeHorizontalRec = root.findViewById(R.id.home_hor_rec);
        homeVerticalRec = root.findViewById(R.id.home_ver_rec);

        //Horizontal RecyclerView
        homeHorModelList = new ArrayList<>();

        homeHorModelList.add(new HomeHorModel(R.drawable.pizza,"Pizza"));
        homeHorModelList.add(new HomeHorModel(R.drawable.hamburger,"Hamburger"));
        homeHorModelList.add(new HomeHorModel(R.drawable.fried_potatoes,"Fries"));
        homeHorModelList.add(new HomeHorModel(R.drawable.ice_cream,"Ice Cream"));
        homeHorModelList.add(new HomeHorModel(R.drawable.sandwich,"Sandwich"));

        homeHorAdapter = new HomeHorAdapter(this,getActivity(),homeHorModelList);
        homeHorizontalRec.setAdapter(homeHorAdapter);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);

        //Vertical RecyclerView
        homeVerModelList = new ArrayList<>();

        homeVerAdapter = new HomeVerAdapter(getActivity(),homeVerModelList);
        homeVerticalRec.setAdapter(homeVerAdapter);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));

        return root;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void callBack(int position, ArrayList<HomeVerModel> list) {
        homeVerAdapter = new HomeVerAdapter(getContext(),list);
        homeVerAdapter.notifyDataSetChanged();
        homeVerticalRec.setAdapter(homeVerAdapter);

    }

}
