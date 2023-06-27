package hou.edu.vn.ngvtuan.food_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hou.edu.vn.ngvtuan.food_app.DataBase.DataBaseHandler;
import hou.edu.vn.ngvtuan.food_app.MainActivity;
import hou.edu.vn.ngvtuan.food_app.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    private DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataBaseHandler = new DataBaseHandler(this);

            binding.btnLogin.setOnClickListener(v -> {
                String phonenumber = binding.logPhoneNumber.getText().toString().trim();
                String password = binding.logPassword.getText().toString().trim();
                if(password.equals("") || phonenumber.equals("")){
                    Toast.makeText(LoginActivity.this,"Vui lòng điền đầy đủ thông tin!",Toast.LENGTH_SHORT).show();
                }else {
                    boolean checkAcc = dataBaseHandler.CheckAccount(phonenumber, password);
                    if (checkAcc) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("phonenumber",phonenumber);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Sai thông tin đăng nhập!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        binding.movelogintoreg.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }
}