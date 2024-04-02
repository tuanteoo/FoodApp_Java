package hou.edu.vn.ngvtuan.food_app.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.Objects;

import hou.edu.vn.ngvtuan.food_app.Activities.AddAddressActivity;
import hou.edu.vn.ngvtuan.food_app.Adapters.DeliveryAddressAdapter;
import hou.edu.vn.ngvtuan.food_app.Interface.OnItemSelectedListener;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.DeliAddressModel;
public class DeliveryAddressFragment extends Fragment implements OnItemSelectedListener {
    ImageView btn_chooseLocation;
    TextView addressDeli,userName,userPhoneNum;
    Button btn_move_addAddress;
    RecyclerView listAddressDeli;
    DeliveryAddressAdapter deliveryAddressAdapter;
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<DeliAddressModel> options;
    private SharedPreferences sharedPreferences;
    Gson gson = new Gson();

    public DeliveryAddressFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delivery_address, container, false);

        btn_chooseLocation = view.findViewById(R.id.btn_chooseLocation);
        addressDeli = view.findViewById(R.id.address_deli);
        userName = view.findViewById(R.id.address_userName);
        userPhoneNum = view.findViewById(R.id.address_phonenumber);
        btn_move_addAddress = view.findViewById(R.id.btn_movetoaddAddress);
        listAddressDeli = view.findViewById(R.id.rec_deliveryAddress);
        listAddressDeli.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

        //Get userID
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();

        //Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference().child("DeliveryAddress").child(userId);
        options = new FirebaseRecyclerOptions.Builder<DeliAddressModel>()
                        .setQuery(databaseReference.orderByChild("selected").equalTo(false), DeliAddressModel.class)
                        .build();

        deliveryAddressAdapter = new DeliveryAddressAdapter(options, this);
        listAddressDeli.setAdapter(deliveryAddressAdapter);

        //Move to Activity Add New Address
        btn_move_addAddress.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AddAddressActivity.class));
        });

        return view;
    }
    @Override
    public void OnItemSelected(DeliAddressModel deliAddressModel) {
        addressDeli.setText(deliAddressModel.getDeliAddress());
        userName.setText(deliAddressModel.getUserName());
        userPhoneNum.setText(deliAddressModel.getPhoneNumber());

        sharedPreferences = requireActivity().getSharedPreferences("selectedAddress", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("address", deliAddressModel.getDeliAddress());
        editor.putString("userName", deliAddressModel.getUserName());
        editor.putString("phoneNumber", deliAddressModel.getPhoneNumber());
        editor.putString("oldSelectedItem",gson.toJson(deliAddressModel));
        editor.putString("oldSelectedItemKey",deliAddressModel.getKeyAddress());
        editor.apply();
    }
    @Override
    public void onStart() {
        super.onStart();
        deliveryAddressAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        deliveryAddressAdapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences = requireActivity().getSharedPreferences("selectedAddress", Context.MODE_PRIVATE);
        addressDeli.setText(sharedPreferences.getString("address", ""));;
        userName.setText(sharedPreferences.getString("userName", ""));
        userPhoneNum.setText(sharedPreferences.getString("phoneNumber", ""));
        String oldSelectedItemJson = sharedPreferences.getString("oldSelectedItem", "");
        String oldSelectedItemKey = sharedPreferences.getString("oldSelectedItemKey","");

        if (oldSelectedItemKey != null && oldSelectedItemJson != null){
            DeliAddressModel oldSelectedItem = gson.fromJson(oldSelectedItemJson,DeliAddressModel.class);
            deliveryAddressAdapter.setOldSelectedItem(oldSelectedItem,oldSelectedItemKey);
        }

    }
}