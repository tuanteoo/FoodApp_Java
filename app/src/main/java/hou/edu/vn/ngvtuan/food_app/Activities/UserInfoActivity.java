package hou.edu.vn.ngvtuan.food_app.Activities;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import hou.edu.vn.ngvtuan.food_app.databinding.ActivityUserInfoBinding;

public class UserInfoActivity extends AppCompatActivity {

    ActivityUserInfoBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("userInfor").child(userId);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String UrlPhoto = snapshot.child("userAvatar").getValue(String.class);
                String userName = snapshot.child("userName").getValue(String.class);
                String userEmail = snapshot.child("userEmail").getValue(String.class);

                Glide.with(UserInfoActivity.this).load(UrlPhoto).into(binding.userAvatar);
                binding.userEmail.setText(userEmail);
                binding.userEmail.setEnabled(false);
                binding.userName.setText(userName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("UserInforActivity","Get error when get data User");
            }
        });
       //Button back home
        binding.backHome.setOnClickListener(v -> {
            finish();
        });
    }
}
