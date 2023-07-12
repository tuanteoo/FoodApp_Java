package hou.edu.vn.ngvtuan.food_app.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.ui.cart.MyCartFragment;

public class LayoutMyCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_my_cart);

        // Add the MyCartFragment to the activity's layout
        MyCartFragment myCartFragment = new MyCartFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, myCartFragment)
                .commit();
    }
}