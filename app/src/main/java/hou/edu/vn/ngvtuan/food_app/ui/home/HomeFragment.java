package hou.edu.vn.ngvtuan.food_app.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import hou.edu.vn.ngvtuan.food_app.Activities.UserInfoActivity;
import hou.edu.vn.ngvtuan.food_app.Adapters.HomeHorAdapter;
import hou.edu.vn.ngvtuan.food_app.Adapters.HomeVerAdapter;
import hou.edu.vn.ngvtuan.food_app.Adapters.SliderAdapter;
import hou.edu.vn.ngvtuan.food_app.Interface.UpdateVerticalRec;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.HomeHorModel;
import hou.edu.vn.ngvtuan.food_app.models.HomeVerModel;
import hou.edu.vn.ngvtuan.food_app.models.SliderModel;

public class HomeFragment extends Fragment implements UpdateVerticalRec {
    RecyclerView homeHorizontalRec,homeVerticalRec;
    ArrayList<HomeHorModel> homeHorModelList;
    HomeHorAdapter homeHorAdapter;
    HomeVerAdapter homeVerAdapter;
    ImageButton imageButton;
    DatabaseReference databaseReference;
    TextView deliAddress;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Food");
        //Slider
        SliderView sliderView = view.findViewById(R.id.slider);

        ArrayList<SliderModel> sliderModels = new ArrayList<>();
        sliderModels.add(new SliderModel(R.drawable.slider1));
        sliderModels.add(new SliderModel(R.drawable.slider2));
        sliderModels.add(new SliderModel(R.drawable.slider3));
        sliderModels.add(new SliderModel(R.drawable.slider4));
        sliderModels.add(new SliderModel(R.drawable.slider5));

        // passing this array list inside our adapter class.
        SliderAdapter adapter = new SliderAdapter(getContext(), sliderModels);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapter);
        sliderView.setScrollTimeInSec(2);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        //Move to information User
        imageButton = view.findViewById(R.id.img_btn_UserInfo);
        imageButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UserInfoActivity.class);
            startActivity(intent);
        });

        homeHorizontalRec = view.findViewById(R.id.home_hor_rec);
        homeVerticalRec = view.findViewById(R.id.home_ver_rec);

        //Horizontal RecyclerView
        homeHorModelList = new ArrayList<>();

        homeHorModelList.add(new HomeHorModel(R.drawable.pizza,"Pizza"));
        homeHorModelList.add(new HomeHorModel(R.drawable.hamburger,"Hamburger"));
        homeHorModelList.add(new HomeHorModel(R.drawable.fried_potatoes,"Fries"));
        homeHorModelList.add(new HomeHorModel(R.drawable.ice_cream,"Ice Cream"));
        homeHorModelList.add(new HomeHorModel(R.drawable.sandwich,"Sandwich"));

        homeHorAdapter = new HomeHorAdapter(this,databaseReference,getActivity(),homeHorModelList);
        homeHorizontalRec.setAdapter(homeHorAdapter);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);

        //Vertical RecyclerView
        FirebaseRecyclerOptions<HomeVerModel> options =
                new FirebaseRecyclerOptions.Builder<HomeVerModel>()
                        .setQuery(databaseReference.orderByChild("typeFood").equalTo(homeHorModelList.get(0).getName()), HomeVerModel.class)
                        .build();

        homeVerAdapter = new HomeVerAdapter(options);
        homeVerticalRec.setAdapter(homeVerAdapter);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        homeVerAdapter.startListening();

        //Shared View Model
        deliAddress = view.findViewById(R.id.home_address);

        EditText sFoodname = view.findViewById(R.id.search_foodName);
        ImageButton btnSearch = view.findViewById(R.id.btn_findFood);
        RelativeLayout relayAddress = view.findViewById(R.id.relay_address);

        relayAddress.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.nav_delivery_address);
        });

        btnSearch.setOnClickListener(v -> {
            String foodname = sFoodname.getText().toString().trim();
        });

        return view;
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void callBack(int position, ArrayList<HomeVerModel> list) {
        // Stop listening to previous data
        homeVerAdapter.stopListening();

        // Create a new query to fetch data based on the selected typeFood
        Query query = databaseReference.orderByChild("typeFood").equalTo(homeHorModelList.get(position).getName());

        // Configure FirebaseRecyclerOptions with the new query
        FirebaseRecyclerOptions<HomeVerModel> options =
                new FirebaseRecyclerOptions.Builder<HomeVerModel>()
                        .setQuery(query, HomeVerModel.class)
                        .build();

        // Update the adapter with new options and start listening for changes
        homeVerAdapter = new HomeVerAdapter(options);
        homeVerAdapter.notifyDataSetChanged();
        homeVerticalRec.setAdapter(homeVerAdapter);
        homeVerAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        homeVerAdapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("selectedAddress", Context.MODE_PRIVATE);
        deliAddress.setText(sharedPreferences.getString("address",""));
    }
}
