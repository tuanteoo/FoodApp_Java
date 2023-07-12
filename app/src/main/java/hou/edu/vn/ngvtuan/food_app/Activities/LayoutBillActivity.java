package hou.edu.vn.ngvtuan.food_app.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.ui.bill.BillFragment;

public class LayoutBillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_bill);

        BillFragment billFragment = new BillFragment();
        // Replace the current fragment with BillFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_containerBill, billFragment)
                .addToBackStack(null)
                .commit();
    }
}