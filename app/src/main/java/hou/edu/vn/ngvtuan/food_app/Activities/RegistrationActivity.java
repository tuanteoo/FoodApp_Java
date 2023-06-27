package hou.edu.vn.ngvtuan.food_app.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hou.edu.vn.ngvtuan.food_app.DataBase.DataBaseHandler;
import hou.edu.vn.ngvtuan.food_app.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends AppCompatActivity {

    ActivityRegistrationBinding binding;
    private DataBaseHandler dataBaseHandler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataBaseHandler = new DataBaseHandler(this);

        binding.btnRegister.setOnClickListener(v -> {
            String username = binding.redEditName.getText().toString().trim();
            String phonenumber = binding.redEditphonenumber.getText().toString().trim();
            String password = binding.redEditpassword.getText().toString().trim();
            if(username.equals("") || password.equals("") || phonenumber.equals("")){
                Toast.makeText(RegistrationActivity.this,"Vui lòng điền đầy đủ thông tin!",Toast.LENGTH_SHORT).show();
            }
            else {
                boolean checkphonenumber = dataBaseHandler.Checkphonenumber(phonenumber);
                if (!checkphonenumber){
                    boolean insert = dataBaseHandler.InsertData(username,"","",phonenumber,password);
                    if (insert){
                        Toast.makeText(RegistrationActivity.this,"Đăng ký tài khoản thành công!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(RegistrationActivity.this,"Đăng ký tài khoản thất bại!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.moveregtologin.setOnClickListener(v -> {
            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

}