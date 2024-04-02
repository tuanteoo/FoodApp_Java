package hou.edu.vn.ngvtuan.food_app.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import hou.edu.vn.ngvtuan.food_app.Adapters.OrderComingAdapter;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.BillModel;

public class OrderComingFragment extends Fragment {
    RecyclerView listOrder;
    OrderComingAdapter orderComingAdapter;
    public OrderComingFragment() {
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_coming, container, false);

        listOrder = view.findViewById(R.id.listOrder_rec_coming);
        listOrder.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Bill");

        ArrayList<BillModel> billModels = new ArrayList<>();
        databaseReference.orderByChild("userID").equalTo(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                billModels.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    BillModel billModel = dataSnapshot.getValue(BillModel.class);
                    if (billModel!= null && billModel.getStatusBill().equals("Waiting") ||billModel!= null &&  billModel.getStatusBill().equals("Coming")){
                        billModels.add(billModel);
                    }
                }
                orderComingAdapter = new OrderComingAdapter(billModels);
                orderComingAdapter.setRecyclerView(listOrder);
                listOrder.setAdapter(orderComingAdapter);
                orderComingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

            orderComingAdapter.saveTimerState();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (orderComingAdapter != null) {
            orderComingAdapter.restoreTimerState();
        }
    }
}