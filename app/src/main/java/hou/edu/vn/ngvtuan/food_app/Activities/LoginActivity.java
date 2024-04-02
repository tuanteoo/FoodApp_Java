package hou.edu.vn.ngvtuan.food_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

import hou.edu.vn.ngvtuan.food_app.MainActivity;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    GoogleSignInClient googleSignInClient;
    GoogleSignInOptions gso;
    private final int RC_SIGN_IN = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                        .requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this,gso);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!= null){
            startActivity(new Intent(this, MainActivity.class));
        }

        binding.btnLLoginwGG.setOnClickListener(v -> SignIn());

        binding.btnLogin.setOnClickListener(v -> {

        });

        binding.movelogintoreg.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }
    private void SignIn() {
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthentication(account.getIdToken());
            }
            catch (Exception e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void firebaseAuthentication(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        FirebaseUser user = auth.getCurrentUser();

                        HashMap<String,Object> map = new HashMap<>();
                        assert user != null;
                        map.put("userName",user.getDisplayName());
                        map.put("userEmail",user.getEmail());
                        map.put("userPhonenumber"," ");
                        map.put("userGender","");
                        map.put("userDateofbirth","");
                        map.put("userAvatar", Objects.requireNonNull(user.getPhotoUrl()).toString());

                        database.getReference().child("userInfor").child(user.getUid()).setValue(map);

                        startActivity(new Intent(this, MainActivity.class));
                    }
                    else {
                        Toast.makeText(this,"Có lỗi xảy ra khi đăng nhập",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}