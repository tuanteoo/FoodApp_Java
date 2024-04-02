package hou.edu.vn.ngvtuan.food_app.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import hou.edu.vn.ngvtuan.food_app.Adapters.PaymentAdapter;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.BillModel;
import hou.edu.vn.ngvtuan.food_app.models.CartModel;

public class PaymentFragment extends Fragment {
    TextView addressDeli,userName,phoneNumber,totalPrice,taxDeli,tax,subTotal,note,paymentBanking,paymentCash;
    RecyclerView listFoodOrder;
    ImageView btn_movetoDeliAddress;
    Button btn_PaymentOrder;
    PaymentAdapter paymentAdapter;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    private final HashSet<Integer> generatedNumbers = new HashSet<>();

    public PaymentFragment() {

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        addressDeli = view.findViewById(R.id.address_deli);
        userName = view.findViewById(R.id.address_userName);
        phoneNumber = view.findViewById(R.id.address_phonenumber);
        totalPrice = view.findViewById(R.id.totalPrice);
        taxDeli = view.findViewById(R.id.tax_delivery);
        tax = view.findViewById(R.id.tax);
        subTotal = view.findViewById(R.id.subtotal);
        note = view.findViewById(R.id.note);
        paymentBanking =view.findViewById(R.id.pm_banking);
        paymentCash = view.findViewById(R.id.pm_cash);
        btn_PaymentOrder = view.findViewById(R.id.btn_PaymentOrder);
        btn_movetoDeliAddress = view.findViewById(R.id.moveto_delivery);
        listFoodOrder = view.findViewById(R.id.list_foodOrder);
        listFoodOrder.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

        //Firebase
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Bill");

        //Set Payment Method
        final String[] paymentMethod = new String[]{"Tiền mặt"};
        paymentCash.setOnClickListener(v -> {
            paymentCash.setBackgroundResource(R.drawable.text_bg_red);
            paymentCash.setTextColor(ContextCompat.getColor(requireContext(),R.color.purple_500));

            paymentBanking.setBackgroundResource(R.drawable.text_bg_gray);
            paymentBanking.setTextColor(ContextCompat.getColor(requireContext(),R.color.black));

            paymentMethod[0] = paymentCash.getText().toString();
        });

        paymentBanking.setOnClickListener(v -> {
            paymentBanking.setBackgroundResource(R.drawable.text_bg_red);
            paymentBanking.setTextColor(ContextCompat.getColor(requireContext(),R.color.purple_500));

            paymentCash.setBackgroundResource(R.drawable.text_bg_gray);
            paymentCash.setTextColor(ContextCompat.getColor(requireContext(),R.color.black));

            paymentMethod[0] = paymentBanking.getText().toString();
        });

        //Move to Delivery Address
        btn_movetoDeliAddress.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.nav_delivery_address);
        });

        // Get Arraylist CartModel From CartFragment
        Bundle bundle = getArguments();
        if (bundle != null){
            ArrayList<CartModel> cartModels = (ArrayList<CartModel>) bundle.getSerializable("cartItems");

            paymentAdapter = new PaymentAdapter(cartModels);
            listFoodOrder.setAdapter(paymentAdapter);

            //Calculate Total
            int sub_Total = 0;
            for (CartModel cartModel: cartModels){
                sub_Total += cartModel.getPrice_quantity();
            }
            subTotal.setText(String.valueOf(sub_Total));
            int total_Price = sub_Total + 6;
            totalPrice.setText(String.valueOf(total_Price));

            btn_PaymentOrder.setOnClickListener(v -> {

                //Create unique key bill
                String currentDate = new SimpleDateFormat("ddMMy", Locale.getDefault()).format(new Date());
                Random rand = new Random();
                int randomNum;
                do {
                    randomNum = rand.nextInt((999999999 - 100000000) + 1) + 100000000;
                } while (generatedNumbers.contains(randomNum));
                generatedNumbers.add(randomNum);
                String billKey = currentDate.substring(0,4)+currentDate.substring(7) + "-" + randomNum;

                //Get the current time
                String currentDatetime = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(new Date());

                //Insert new Bill to Firebase
                databaseReference.child(Objects.requireNonNull(billKey))
                        .setValue(new BillModel(billKey,userId,currentDatetime,"",String.valueOf(cartModels.size()),String.valueOf(total_Price),paymentMethod[0],"Waiting",userName.getText().toString(),addressDeli.getText().toString(),phoneNumber.getText().toString(),cartModels));

                //Move to HistoryFragment
                Navigation.findNavController(requireView()).navigate(R.id.nav_history);
            });
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        sharedPreferences = requireActivity().getSharedPreferences("selectedAddress", Context.MODE_PRIVATE);
        addressDeli.setText(sharedPreferences.getString("address", ""));
        userName.setText(sharedPreferences.getString("userName", ""));
        phoneNumber.setText(sharedPreferences.getString("phoneNumber", ""));
    }
}