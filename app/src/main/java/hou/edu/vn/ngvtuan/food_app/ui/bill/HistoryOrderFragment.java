package hou.edu.vn.ngvtuan.food_app.ui.bill;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hou.edu.vn.ngvtuan.food_app.Adapters.HistoryOrderAdapter;
import hou.edu.vn.ngvtuan.food_app.R;

public class HistoryOrderFragment extends Fragment {
    RecyclerView recyclerView;
    HistoryOrderAdapter billOrderAdapter;

    public HistoryOrderFragment() {
        // Required empty public constructor
    }

    @SuppressLint({"MissingInflatedId", "NotifyDataSetChanged"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_order, container, false);

        recyclerView = view.findViewById(R.id.bill_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

        // Retrieve data from the ListBill table

        //billOrderAdapter = new BillOrderAdapter(billModels);
        recyclerView.setAdapter(billOrderAdapter);

        return view;
    }
}