package hou.edu.vn.ngvtuan.food_app.ui.cart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import hou.edu.vn.ngvtuan.food_app.Adapters.CartAdapter;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.CartModel;

public class MyCartFragment extends Fragment {
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    CartAdapter cartAdapter;

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

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Cart").child(userId);

        FirebaseRecyclerOptions<CartModel> options =
                new FirebaseRecyclerOptions.Builder<CartModel>()
                        .setQuery(databaseReference, CartModel.class)
                        .build();

        cartAdapter = new CartAdapter(options);
        recyclerView.setAdapter(cartAdapter);

        Button btnMakeOrder = view.findViewById(R.id.make_oder);
        btnMakeOrder.setOnClickListener(v -> {
            if (options.getSnapshots().isEmpty()){
                Toast.makeText(getContext(),"Giỏ hàng của bạn đang trống!",Toast.LENGTH_SHORT).show();
            }else {
                Navigation.findNavController(requireView()).navigate(R.id.nav_payment);
            }
        });

        ImageView btnBackHome = view.findViewById(R.id.back_home);
        btnBackHome.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.nav_home);
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        cartAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        cartAdapter.stopListening();
    }
}