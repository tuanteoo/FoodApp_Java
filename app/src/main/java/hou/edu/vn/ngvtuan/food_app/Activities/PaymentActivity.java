package hou.edu.vn.ngvtuan.food_app.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hou.edu.vn.ngvtuan.food_app.DataBase.DataBaseHandler;
import hou.edu.vn.ngvtuan.food_app.databinding.ActivityPaymentBinding;

public class PaymentActivity extends AppCompatActivity {
    ActivityPaymentBinding binding;
    private DataBaseHandler dataBaseHandler;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Get Total Price int Cart
        int totalPrice = getIntent().getIntExtra("totalPrice",0);
        binding.pmTotalPrice.setText("$"+totalPrice);

        // Payment bill
        binding.btnPaymentBill.setOnClickListener(v -> {
            String username = binding.editTextPaymentName.getText().toString().trim();
            String phonenumber = binding.editTextPaymentPhone.getText().toString().trim();
            String address = binding.editTextPaymentAddress.getText().toString().trim();
            if (username.equals("") || phonenumber.equals("") || address.equals("")){
                Toast.makeText(getApplicationContext(),"Vui lòng điền đủ thông tin!",Toast.LENGTH_SHORT).show();
            }else {
                dataBaseHandler = new DataBaseHandler(PaymentActivity.this);
                boolean check = dataBaseHandler.insertDataBill(username,phonenumber,address,totalPrice);
                if (check){
                    // Call the DeleteCartOrder method
                    Toast.makeText(getApplicationContext(),"Thanh toán thành công!",Toast.LENGTH_SHORT).show();
                    dataBaseHandler.DeleteCartOrder();

                    Intent intent = new Intent(PaymentActivity.this,LayoutBillActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Lỗi!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Button Cancel Payment
        binding.btnCancelOrder.setOnClickListener(v -> finish());
    }
}