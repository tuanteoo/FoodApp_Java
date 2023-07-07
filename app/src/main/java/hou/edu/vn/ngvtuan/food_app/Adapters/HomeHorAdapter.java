package hou.edu.vn.ngvtuan.food_app.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.HomeHorModel;
import hou.edu.vn.ngvtuan.food_app.models.HomeVerModel;

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.ViewHolder> {
    UpdateVerticalRec updateVerticalRec;
    Activity activity;
    ArrayList<HomeHorModel> list;
    boolean check = true;
    boolean select = true;
    int row_index = -1;


    public HomeHorAdapter(UpdateVerticalRec updateVerticalRec, Activity activity, ArrayList<HomeHorModel> list) {
        this.updateVerticalRec = updateVerticalRec;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeHorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal_iitem, parent, false));
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull HomeHorAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());

        if (check) {
            ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();

            homeVerModels.add(new HomeVerModel(R.drawable.pizza1, "Pizza 1", "10:00 - 23:00", "4.9", "56"));
            homeVerModels.add(new HomeVerModel(R.drawable.pizza2, "Pizza 2", "7:00 - 22:00", "5.0", "78"));
            homeVerModels.add(new HomeVerModel(R.drawable.pizza3, "Pizza 3", "8:00 - 13:00", "4.5", "34"));
            homeVerModels.add(new HomeVerModel(R.drawable.pizza4, "Pizza 4", "17:00 - 23:59", "4.8", "40"));

            updateVerticalRec.callBack(position, homeVerModels);
            check = false;
        }
        holder.cardView.setOnClickListener(v -> {
            row_index = position;
            notifyDataSetChanged();

            if(position == 0){
                ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();

                homeVerModels.add(new HomeVerModel(R.drawable.pizza1,"Pizza 1","10:00 - 23:00","4.9","56"));
                homeVerModels.add(new HomeVerModel(R.drawable.pizza2,"Pizza 2","7:00 - 22:00","5.0","78"));
                homeVerModels.add(new HomeVerModel(R.drawable.pizza3,"Pizza 3","8:00 - 13:00","4.5","34"));
                homeVerModels.add(new HomeVerModel(R.drawable.pizza4,"Pizza 4","17:00 - 23:59","4.8","40"));

                updateVerticalRec.callBack(position, homeVerModels);
            }
            else if(position == 1){
                ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();

                homeVerModels.add(new HomeVerModel(R.drawable.burger1,"Hamburger 1","10:00 - 23:00","4.9","56"));
                homeVerModels.add(new HomeVerModel(R.drawable.burger2,"Hamburger 2","7:00 - 22:00","5.0","78"));
                homeVerModels.add(new HomeVerModel(R.drawable.burger4,"Hamburger 3","8:00 - 13:00","4.5","34"));

                updateVerticalRec.callBack(position, homeVerModels);
            }
            else if(position == 2){
                ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();

                homeVerModels.add(new HomeVerModel(R.drawable.fries1,"Khoai Tây Chiên 1","10:00 - 23:00","4.9","56"));
                homeVerModels.add(new HomeVerModel(R.drawable.fries2,"Khoai Tây Chiên 2","7:00 - 22:00","5.0","78"));
                homeVerModels.add(new HomeVerModel(R.drawable.fries3,"Khoai Tây Chiên 3","8:00 - 13:00","4.5","34"));
                homeVerModels.add(new HomeVerModel(R.drawable.fries4,"Khoai Tây Chiên 4","17:00 - 23:59","4.8","40"));

                updateVerticalRec.callBack(position, homeVerModels);
            }
            else if(position == 3){
                ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();

                homeVerModels.add(new HomeVerModel(R.drawable.icecream1,"Kem 1","10:00 - 23:00","4.9","56"));
                homeVerModels.add(new HomeVerModel(R.drawable.icecream2,"Kem 2","7:00 - 22:00","5.0","78"));
                homeVerModels.add(new HomeVerModel(R.drawable.icecream3,"Kem 3","8:00 - 13:00","4.5","34"));
                homeVerModels.add(new HomeVerModel(R.drawable.icecream4,"Kem 4","17:00 - 23:59","4.8","40"));

                updateVerticalRec.callBack(position, homeVerModels);
            }
            else if(position == 4){
                ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();

                homeVerModels.add(new HomeVerModel(R.drawable.sandwich1,"Sandwich 1","10:00 - 23:00","4.9","56"));
                homeVerModels.add(new HomeVerModel(R.drawable.sandwich2,"Sandwich 2","7:00 - 22:00","5.0","78"));
                homeVerModels.add(new HomeVerModel(R.drawable.sandwich3,"Sandwich 3","8:00 - 13:00","4.5","34"));
                homeVerModels.add(new HomeVerModel(R.drawable.sandwich4,"Sandwich 4","17:00 - 23:59","4.8","40"));

                updateVerticalRec.callBack(position, homeVerModels);
            }
        });
            if(select){
                if(position == 0){
                    holder.cardView.setBackgroundResource(R.drawable.change_bg);
                    select = false;
                }
            }
            else {
                if (row_index == position) {
                    holder.cardView.setBackgroundResource(R.drawable.change_bg);
                }
                else{
                    holder.cardView.setBackgroundResource(R.drawable.default_bg);
                }
            }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.hor_img);
            name = itemView.findViewById(R.id.hor_text);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
