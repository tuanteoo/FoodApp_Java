package hou.edu.vn.ngvtuan.food_app.ui.cart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hou.edu.vn.ngvtuan.food_app.Adapters.CartAdapter;
import hou.edu.vn.ngvtuan.food_app.DataBase.DataBaseHandler;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.CartModel;

public class MyCartFragment extends Fragment {
    List<CartModel> cartModelList;
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    private DataBaseHandler dataBaseHandler;
    public MyCartFragment() {

    }


    @SuppressLint({"MissingInflatedId", "SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        recyclerView = view.findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

        // Retrieve data from the orderlist table
        dataBaseHandler = new DataBaseHandler(getContext());
        cartModelList = dataBaseHandler.getAllDataOrder();
        cartAdapter = new CartAdapter(cartModelList);
        recyclerView.setAdapter(cartAdapter);

        // Display the total price
        TextView totalPriceTextView = view.findViewById(R.id.TotalPrice);
        int totalPrice = dataBaseHandler.getTotalPrice();
        totalPriceTextView.setText(""+totalPrice);

        Button btnMakeOrder = view.findViewById(R.id.make_oder);
        btnMakeOrder.setOnClickListener(v -> {
            // Delete all data from the orderlist table
            dataBaseHandler.MakeOrder();

            // Update the RecyclerView
            cartModelList.clear();
            cartAdapter.notifyDataSetChanged();
        });
        return view;
    }

}