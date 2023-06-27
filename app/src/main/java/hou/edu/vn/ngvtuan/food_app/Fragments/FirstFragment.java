package hou.edu.vn.ngvtuan.food_app.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hou.edu.vn.ngvtuan.food_app.Adapters.FeaturedAdapter;
import hou.edu.vn.ngvtuan.food_app.Adapters.FeaturedVerAdapter;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.FeaturedModel;
import hou.edu.vn.ngvtuan.food_app.models.FeaturedVerModel;


public class FirstFragment extends Fragment {

    List<FeaturedModel> featuredModelList;
    List<FeaturedVerModel> featuredVerModelList;
    RecyclerView recyclerView;
    FeaturedAdapter featuredAdapter;
    FeaturedVerAdapter featuredVerAdapter;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        //Hor
        recyclerView = view.findViewById(R.id.featured_hor_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        featuredModelList = new ArrayList<>();

        featuredModelList.add(new FeaturedModel(R.drawable.fav1,"Nổi bật 1","Miêu tả 1"));
        featuredModelList.add(new FeaturedModel(R.drawable.fav2,"Nổi bật 2","Miêu tả 2"));
        featuredModelList.add(new FeaturedModel(R.drawable.fav3,"Nổi bật 3","Miêu tả 3"));

        featuredAdapter = new FeaturedAdapter(featuredModelList);
        recyclerView.setAdapter(featuredAdapter);

        //Ver
        recyclerView = view.findViewById(R.id.featured_ver_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        featuredVerModelList = new ArrayList<>();

        featuredVerModelList.add(new FeaturedVerModel(R.drawable.ver1,"Nổi Bật 1","Miêu tả 1","4.9","07:00 - 12:00"));
        featuredVerModelList.add(new FeaturedVerModel(R.drawable.ver2,"Nổi Bật 2","Miêu tả 2","4.7","06:30 - 13:00"));
        featuredVerModelList.add(new FeaturedVerModel(R.drawable.ver3,"Nổi Bật 3","Miêu tả 3","4.9","06:00 - 10:00"));

        featuredVerAdapter = new FeaturedVerAdapter(featuredVerModelList);
        recyclerView.setAdapter(featuredVerAdapter);

        return view;
    }
}