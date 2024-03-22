package hou.edu.vn.ngvtuan.food_app.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import hou.edu.vn.ngvtuan.food_app.Adapters.PaymentAdapter;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.CartModel;

public class PaymentFragment extends Fragment {
    TextView addressDeli,userName,userPhone,totalPrice,taxDeli,tax,subTotal,note;
    RecyclerView listFoodOrder;
    ImageView btn_backToCart;
    Button btn_PaymentOrder;
    PaymentAdapter paymentAdapter;
    DatabaseReference databaseReference;

    public PaymentFragment() {

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        addressDeli = view.findViewById(R.id.address_deli);
        userName = view.findViewById(R.id.address_userName);
        userPhone = view.findViewById(R.id.address_phonenumber);
        totalPrice = view.findViewById(R.id.totalPrice);
        taxDeli = view.findViewById(R.id.tax_delivery);
        tax = view.findViewById(R.id.tax);
        subTotal = view.findViewById(R.id.subtotal);
        note = view.findViewById(R.id.note);
        btn_backToCart = view.findViewById(R.id.back_cart);
        btn_PaymentOrder = view.findViewById(R.id.btn_PaymentOrder);
        listFoodOrder = view.findViewById(R.id.list_foodOrder);
        listFoodOrder.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Cart").child(userId);
        FirebaseRecyclerOptions<CartModel> options =
                new FirebaseRecyclerOptions.Builder<CartModel>()
                        .setQuery(databaseReference, CartModel.class)
                        .build();
        paymentAdapter = new PaymentAdapter(options);
        listFoodOrder.setAdapter(paymentAdapter);


        btn_backToCart.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.nav_my_cart);
        });
        btn_PaymentOrder.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.nav_history);
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        paymentAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        paymentAdapter.stopListening();
    }
}