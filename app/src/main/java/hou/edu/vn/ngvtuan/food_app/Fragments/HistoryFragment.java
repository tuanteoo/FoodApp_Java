package hou.edu.vn.ngvtuan.food_app.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

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

import hou.edu.vn.ngvtuan.food_app.Adapters.HistoryAdapter;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.BillModel;


public class HistoryFragment extends Fragment {
    Spinner typeStatus;
    TextView dateStart,dateEnd;
    RecyclerView listBill;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @SuppressLint({"MissingInflatedId", "NotifyDataSetChanged"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_history, container, false);

        typeStatus = view.findViewById(R.id.typeStatus);
        dateStart = view.findViewById(R.id.date_start);
        dateEnd = view.findViewById(R.id.date_end);
        listBill = view.findViewById(R.id.listBill_rec);
        listBill.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));

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
                    if (billModel != null && billModel.getStatusBill().equals("Đã huỷ") ||billModel != null && billModel.getStatusBill().equals("Hoàn thành")){
                        billModels.add(billModel);
                    }
                }
                HistoryAdapter historyAdapter = new HistoryAdapter(billModels);
                listBill.setAdapter(historyAdapter);
                historyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("HistoryFragment","Get error:" + error.getMessage() );
            }
        });

        String[] values = {"Trạng thái", "Đã huỷ", "Hoàn thành"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        typeStatus.setAdapter(adapter);
        typeStatus.setSelection(0);

//        typeStatus.setOnItemClickListener((parent, view1, position, id) -> {
//            String selectedItem = (String) parent.getItemAtPosition(position);
//            ArrayList<BillModel> billModelsCancel = new ArrayList<>();
//            if (position != 0){
//                billModelsCancel.clear();
//                for (BillModel billModel: billModels){
//                    if (billModel.getStatusBill().equals(selectedItem)){
//                        billModelsCancel.add(billModel);
//                    }
//                }
//                HistoryAdapter historyAdapter = new HistoryAdapter(billModelsCancel);
//                listBill.setAdapter(historyAdapter);
//                historyAdapter.notifyDataSetChanged();
//            }else {
//                HistoryAdapter historyAdapter = new HistoryAdapter(billModels);
//                listBill.setAdapter(historyAdapter);
//                historyAdapter.notifyDataSetChanged();
//            }
//
//        });

        return view;
    }
}