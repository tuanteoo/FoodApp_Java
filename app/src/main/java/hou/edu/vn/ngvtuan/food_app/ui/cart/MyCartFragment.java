package hou.edu.vn.ngvtuan.food_app.ui.cart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        recyclerView = view.findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

//        cartAdapter = new CartAdapter(cartModelList);
//        recyclerView.setAdapter(cartAdapter);

        return view;
    }

}