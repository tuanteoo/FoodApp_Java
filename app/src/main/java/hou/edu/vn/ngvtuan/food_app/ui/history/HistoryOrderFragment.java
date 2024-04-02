package hou.edu.vn.ngvtuan.food_app.ui.history;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import hou.edu.vn.ngvtuan.food_app.Adapters.ViewPagerAdapter;
import hou.edu.vn.ngvtuan.food_app.Fragments.HistoryFragment;
import hou.edu.vn.ngvtuan.food_app.Fragments.OrderComingFragment;
import hou.edu.vn.ngvtuan.food_app.R;

public class HistoryOrderFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    public HistoryOrderFragment() {
        // Required empty public constructor
    }

    @SuppressLint({"MissingInflatedId", "NotifyDataSetChanged"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_order, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager2 = view.findViewById(R.id.view_Fragment);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), getLifecycle());
        viewPagerAdapter.addFragment(new OrderComingFragment(),"Đang đến");
        viewPagerAdapter.addFragment(new HistoryFragment(),"Lịch sử");

        viewPager2.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(viewPagerAdapter.getPageTitle(position))
        ).attach();

        return view;
    }
}