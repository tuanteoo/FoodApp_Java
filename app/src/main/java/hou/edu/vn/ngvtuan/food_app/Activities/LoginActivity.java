package hou.edu.vn.ngvtuan.food_app.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hou.edu.vn.ngvtuan.food_app.DataBase.DataBaseHandler;
import hou.edu.vn.ngvtuan.food_app.MainActivity;
import hou.edu.vn.ngvtuan.food_app.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private DataBaseHandler dataBaseHandler;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataBaseHandler = new DataBaseHandler(this);

        //// Add these lines to retrieve the saved login information from SharedPreferences
        sharedPreferences = getSharedPreferences("SaveLogin",MODE_PRIVATE);
        String savedPhoneNumber = sharedPreferences.getString("PhoneNumber", "");
        String savedPassword = sharedPreferences.getString("Password", "");

        if (!savedPhoneNumber.equals("") && !savedPassword.equals("")) {
            boolean checkAcc = dataBaseHandler.CheckAccount(savedPhoneNumber, savedPassword);
            if (checkAcc) {
                Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("phonenumber", savedPhoneNumber);
                startActivity(intent);
            }
        }

        binding.btnLogin.setOnClickListener(v -> {
            String phonenumber = binding.logPhoneNumber.getText().toString().trim();
            String password = binding.logPassword.getText().toString().trim();

            if(password.equals("") || phonenumber.equals("")){
                Toast.makeText(LoginActivity.this,"Vui lòng điền đầy đủ thông tin!",Toast.LENGTH_SHORT).show();
            }else {
                boolean checkAcc = dataBaseHandler.CheckAccount(phonenumber, password);
                if (checkAcc) {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("phonenumber",phonenumber);
                    startActivity(intent);

                    // Add these lines to save the login information in SharedPreferences
                    editor =sharedPreferences.edit();
                    editor.putString("PhoneNumber", phonenumber);
                    editor.putString("Password", password);
                    editor.apply();
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