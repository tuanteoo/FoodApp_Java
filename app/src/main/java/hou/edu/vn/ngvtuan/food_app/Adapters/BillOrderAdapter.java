package hou.edu.vn.ngvtuan.food_app.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.BillModel;

public class BillOrderAdapter extends RecyclerView.Adapter<BillOrderAdapter.ViewHolder> {
    private final ArrayList<BillModel> billModels;

    public BillOrderAdapter(ArrayList<BillModel> billModels) {
        this.billModels = billModels;
    }


    @NonNull
    @Override
    public BillOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BillOrderAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_hisorder_item, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BillOrderAdapter.ViewHolder holder, int position) {
        BillModel billModel = billModels.get(position);

        holder.date.setText(billModel.getDate());
        holder.idBill.setText(""+billModel.getId());
        holder.address.setText(billModel.getAddress());
        holder.totalPrice.setText(""+billModel.getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return billModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idBill,totalPrice,address,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.his_dateOrder);
            idBill = itemView.findViewById(R.id.his_idBill);
            totalPrice = itemView.findViewById(R.id.his_totalPrice);
            address = itemView.findViewById(R.id.his_address);
        }
    }
}
