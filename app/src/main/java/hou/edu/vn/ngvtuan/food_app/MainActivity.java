package hou.edu.vn.ngvtuan.food_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import hou.edu.vn.ngvtuan.food_app.Activities.LoginActivity;
import hou.edu.vn.ngvtuan.food_app.DataBase.DataBaseHandler;
import hou.edu.vn.ngvtuan.food_app.databinding.ActivityMainBinding;
import hou.edu.vn.ngvtuan.food_app.models.UserModel;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    ActivityMainBinding binding;
    private DataBaseHandler dataBaseHandler;
    String phonenumber1;
    public static UserModel userModel;

    @SuppressLint("ApplySharedPref")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_dailymeal, R.id.nav_favorite,R.id.nav_my_cart)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //GetDataUser
        phonenumber1 = getIntent().getStringExtra("phonenumber");

        View view = navigationView.getHeaderView(0);
        TextView hUsername = view.findViewById(R.id.nav_username);
        TextView hPhonenumber = view.findViewById(R.id.nav_phonenumber);

        dataBaseHandler = new DataBaseHandler(this);
        ArrayList<UserModel> listUser = dataBaseHandler.getLogin_User(phonenumber1);
        userModel = listUser.get(0);

        hUsername.setText(userModel.getUsername());
        hPhonenumber.setText(userModel.getPhonenumber());

        //SharePreferences


        //Button Logout
        binding.btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this,"Đăng xuất thành công !",Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}