package hou.edu.vn.ngvtuan.food_app.Activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hou.edu.vn.ngvtuan.food_app.DataBase.DataBaseHandler;
import hou.edu.vn.ngvtuan.food_app.MainActivity;
import hou.edu.vn.ngvtuan.food_app.databinding.ActivityUserInfoBinding;

public class UserInfoActivity extends AppCompatActivity {

    ActivityUserInfoBinding binding;
    private DataBaseHandler dataBaseHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataBaseHandler = new DataBaseHandler(this);

        binding.userName.setText(MainActivity.userModel.getUsername());
        binding.userGender.setText(MainActivity.userModel.getGender());
        binding.userDateofbirth.setText(MainActivity.userModel.getDateOfBirth());
        binding.userPhonenumber.setText(MainActivity.userModel.getPhonenumber());
        binding.userPassword.setText(MainActivity.userModel.getPassword());

        //Button UpdateDataUser
        binding.btnUpdate.setOnClickListener(v -> {
            // Get the old phone number from the userModel object
            String oldPhoneNumber = MainActivity.userModel.getPhonenumber();

            // Get the new values for the user data from the TextViews
            String newPhoneNumber = binding.userPhonenumber.getText().toString();
            String username = binding.userName.getText().toString();
            String gender = binding.userGender.getText().toString();
            String dateofbirth = binding.userDateofbirth.getText().toString();
            String password = binding.userPassword.getText().toString();

            // Update the user data in the database
            boolean success = dataBaseHandler.updateDataUser(oldPhoneNumber, username, gender, dateofbirth, newPhoneNumber, password);

            // Check if the update was successful
            if (success) {
                // The update was successful
                Toast.makeText(UserInfoActivity.this, "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();

                // Update the userModel object with the new values
                MainActivity.userModel.setPhonenumber(newPhoneNumber);
                MainActivity.userModel.setUsername(username);
                MainActivity.userModel.setGender(gender);
                MainActivity.userModel.setDateOfBirth(dateofbirth);
                MainActivity.userModel.setPassword(password);
            } else {
                // The update failed
                Toast.makeText(UserInfoActivity.this, "Cập nhật thông tin thất bại!", Toast.LENGTH_SHORT).show();
            }
        });

        //Button Cancel
        binding.btnCancel.setOnClickListener(v -> finish());
    }
}
