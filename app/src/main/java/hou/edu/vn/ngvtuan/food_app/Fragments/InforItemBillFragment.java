package hou.edu.vn.ngvtuan.food_app.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import hou.edu.vn.ngvtuan.food_app.R;

public class InforItemBillFragment extends Fragment {

    public InforItemBillFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infor_item_bill, container, false);

        return view;
    }
}