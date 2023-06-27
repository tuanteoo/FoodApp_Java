package hou.edu.vn.ngvtuan.food_app.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import hou.edu.vn.ngvtuan.food_app.Fragments.FirstFragment;
import hou.edu.vn.ngvtuan.food_app.Fragments.SecondFragment;
import hou.edu.vn.ngvtuan.food_app.Fragments.ThirdFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new SecondFragment();
            case 2:
                return new ThirdFragment();

        }
        return new FirstFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
