package hou.edu.vn.ngvtuan.food_app.ui.bill;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hou.edu.vn.ngvtuan.food_app.Adapters.BillOrderAdapter;
import hou.edu.vn.ngvtuan.food_app.DataBase.DataBaseHandler;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.BillModel;

public class BillFragment extends Fragment {
    ArrayList<BillModel> billModels;
    RecyclerView recyclerView;
    BillOrderAdapter billOrderAdapter;
    private DataBaseHandler dataBaseHandler;

    public BillFragment() {
        // Required empty public constructor
    }

    @SuppressLint({"MissingInflatedId", "NotifyDataSetChanged"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bill, container, false);

        recyclerView = view.findViewById(R.id.bill_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

        // Retrieve data from the ListBill table
        dataBaseHandler = new DataBaseHandler(getContext());
        billModels = dataBaseHandler.getAllDataBill();
        billOrderAdapter = new BillOrderAdapter(billModels);
        recyclerView.setAdapter(billOrderAdapter);

        Button btnDeleteBill = view.findViewById(R.id.btn_DeleteBill);
        btnDeleteBill.setOnClickListener(v -> {
            dataBaseHandler.DeleteAllBill();

            billModels.clear();
            billOrderAdapter.notifyDataSetChanged();
        });

        return view;
    }
}