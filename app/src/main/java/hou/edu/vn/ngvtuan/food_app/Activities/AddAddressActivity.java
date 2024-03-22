package hou.edu.vn.ngvtuan.food_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import hou.edu.vn.ngvtuan.food_app.databinding.ActivityAddAddressBinding;
import hou.edu.vn.ngvtuan.food_app.models.DeliAddressModel;

public class AddAddressActivity extends AppCompatActivity {
    ActivityAddAddressBinding binding;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("DeliveryAddress").child(userId);
        String deliAddressID = databaseReference.push().getKey();

        Intent intent = getIntent();
        boolean showButton = intent.getBooleanExtra("showDelete", false);
        if (showButton){
            binding.relaBtnDeleteAddress.setVisibility(View.VISIBLE);
        }
        String get_address = intent.getStringExtra("deliAddress");
        String get_username = intent.getStringExtra("username");
        String get_phoneNum = intent.getStringExtra("phonenumber");
        String get_keyAddress = intent.getStringExtra("keyAddress");

        binding.btnDeleteAddress.setOnClickListener(v -> {
            databaseReference.child(Objects.requireNonNull(get_keyAddress)).removeValue();
            finish();
        });


        binding.addNewAddress.setText(get_address);
        binding.addUserName.setText(get_username);
        binding.addUserPhoneNum.setText(get_phoneNum);

        binding.backDelivery.setOnClickListener(v -> {
            finish();
        });

        binding.btnAddAddress.setOnClickListener(v -> {
            String address = binding.addNewAddress.getText().toString();
            String username = binding.addUserName.getText().toString();
            String phoneNum = binding.addUserPhoneNum.getText().toString();

            if (showButton){
                Map<String,Object> map = new HashMap<>();

                map.put("deliAddress",address);
                map.put("userName",username);
                map.put("phoneNumber",phoneNum);

                databaseReference
                        .child(Objects.requireNonNull(get_keyAddress)).updateChildren(map)
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(this, "Address updated successfully", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(this, "Error while Updating", Toast.LENGTH_SHORT).show();
                        });
            }
            else {
                databaseReference.child(Objects.requireNonNull(deliAddressID)).setValue(new DeliAddressModel(deliAddressID,address,username,phoneNum,false));
            }
            finish();
        });

    }
}