package hou.edu.vn.ngvtuan.food_app.ui.cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hou.edu.vn.ngvtuan.food_app.Adapters.CartAdapter;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.CartModel;

public class MyCartFragment extends Fragment {

    List<CartModel> cartModelList;
    CartAdapter cartAdapter;
    RecyclerView recyclerView;

    public MyCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        recyclerView = view.findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        cartModelList = new ArrayList<>();

        cartModelList.add(new CartModel(R.drawable.s1,"Món Đặt 1","4.9","40"));
        cartModelList.add(new CartModel(R.drawable.s2,"Món Đặt 2","4.7","30"));
        cartModelList.add(new CartModel(R.drawable.s3,"Món Đặt 3","4.5","35"));
        cartModelList.add(new CartModel(R.drawable.s4,"Món Đặt 4","5.0","50"));
        cartModelList.add(new CartModel(R.drawable.sandwich2,"Món Đặt 5","4.7","30"));
        cartModelList.add(new CartModel(R.drawable.burger1,"Món Đặt 6","4.5","35"));

        cartAdapter = new CartAdapter(cartModelList);
        recyclerView.setAdapter(cartAdapter);

        return view;
    }
}