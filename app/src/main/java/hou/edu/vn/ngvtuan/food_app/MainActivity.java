package hou.edu.vn.ngvtuan.food_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import hou.edu.vn.ngvtuan.food_app.Activities.LoginActivity;
import hou.edu.vn.ngvtuan.food_app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity  {
    private AppBarConfiguration mAppBarConfiguration;
    ActivityMainBinding binding;
    FirebaseAuth auth;
    GoogleSignInClient googleSignInClient;

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
                R.id.nav_home, R.id.nav_dailymeal, R.id.nav_favorite,R.id.nav_my_cart, R.id.nav_history)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View view = navigationView.getHeaderView(0);
        TextView hUsername = view.findViewById(R.id.nav_username);
        TextView hPhonenumber = view.findViewById(R.id.nav_phonenumber);

        auth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        DatabaseReference userRef = database.getReference().child("userInfor").child(userId);
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String userName = snapshot.child("userName").getValue(String.class);
                String userEmail = snapshot.child("userEmail").getValue(String.class);

                hUsername.setText(userName);
                hPhonenumber.setText(userEmail);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Button Logout
        binding.btnLogout.setOnClickListener(v -> {
            // Firebase sign out
            auth.signOut();

            // Google sign out
            googleSignInClient.signOut().addOnCompleteListener(this,
                    task -> {
                        // Update your UI here
                        // Start LoginActivity
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    });
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