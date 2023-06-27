package hou.edu.vn.ngvtuan.food_app.Activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import hou.edu.vn.ngvtuan.food_app.MainActivity;
import hou.edu.vn.ngvtuan.food_app.databinding.ActivityUserInfoBinding;

public class UserInfoActivity extends AppCompatActivity {

    ActivityUserInfoBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.userName.setText(MainActivity.userModel.getUsername());
        binding.userGender.setText(MainActivity.userModel.getGender());
        binding.userDateofbirth.setText(MainActivity.userModel.getDateOfBirth());
        binding.userPhonenumber.setText(MainActivity.userModel.getPhonenumber());
        binding.userPassword.setText(MainActivity.userModel.getPassword());

        //Button Cancel
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
