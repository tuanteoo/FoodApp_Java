package hou.edu.vn.ngvtuan.food_app.ui.cart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hou.edu.vn.ngvtuan.food_app.Adapters.CartAdapter;
import hou.edu.vn.ngvtuan.food_app.DataBase.DataBaseHandler;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.CartModel;

public class MyCartFragment extends Fragment {
    ArrayList<CartModel> cartModelList;
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    private DataBaseHandler dataBaseHandler;
    TextView totalPriceTextView;

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
        totalPriceTextView = view.findViewById(R.id.TotalPrice);
        updateTotalPrice();
        // Set an OnItemDeletedListener on the adapter
        cartAdapter.setOnItemDeletedListener(this::updateTotalPrice);

        Button btnMakeOrder = view.findViewById(R.id.make_oder);
        btnMakeOrder.setOnClickListener(v -> {
            if (cartModelList.isEmpty()) {
                Toast.makeText(getContext(),"Giỏ hàng trống,vui lòng đặt đồ ăn!",Toast.LENGTH_SHORT).show();
            } else {
                // Delete all data from the orderlist table
                dataBaseHandler.MakeOrder();
                Toast.makeText(getContext(),"Đặt Hàng Thành Công",Toast.LENGTH_SHORT).show();

                // Update the RecyclerView
                cartModelList.clear();
                updateTotalPrice();
                cartAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }
    @SuppressLint("SetTextI18n")
    private void updateTotalPrice() {
        int totalPrice = dataBaseHandler.getTotalPrice();
        totalPriceTextView.setText(""+totalPrice);
    }
}