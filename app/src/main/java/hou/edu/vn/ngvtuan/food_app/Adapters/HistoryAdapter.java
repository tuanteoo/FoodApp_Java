package hou.edu.vn.ngvtuan.food_app.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.BillModel;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<BillModel> billModels;
    private DatabaseReference databaseReference;

    public HistoryAdapter(ArrayList<BillModel> billModels) {
        this.billModels = billModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_history_item, parent, false));
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.idBill.setText(billModels.get(position).getBillKey());
        holder.dateBill.setText(billModels.get(position).getDateBill().substring(0,Math.min(billModels.get(position).getDateBill().length(),10)));
        holder.quantity.setText(billModels.get(position).getQuantity());
        holder.totalPrice.setText(billModels.get(position).getTotalPrice());
        holder.status.setText(billModels.get(position).getStatusBill());

        FoodOrderComingAdapter foodOrderComingAdapter = new FoodOrderComingAdapter(billModels.get(position).getCartModels());
        holder.listFoodHis.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(),RecyclerView.HORIZONTAL,false));
        holder.listFoodHis.setAdapter(foodOrderComingAdapter);
        foodOrderComingAdapter.notifyDataSetChanged();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Cart").child(userId);

        holder.btn_reOrder.setOnClickListener(v -> {
            databaseReference.setValue(billModels.get(position).getCartModels());
        });
    }

    @Override
    public int getItemCount() {
        return billModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idBill,dateBill,quantity,totalPrice,status;
        RecyclerView listFoodHis;
        Button btn_reOrder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idBill = itemView.findViewById(R.id.item_history_idBill);
            dateBill = itemView.findViewById(R.id.item_history_dateBill);
            quantity = itemView.findViewById(R.id.item_history_quantity);
            totalPrice = itemView.findViewById(R.id.item_history_totalPrice);
            status = itemView.findViewById(R.id.item_history_status);
            listFoodHis = itemView.findViewById(R.id.item_history_recListFood);
            btn_reOrder = itemView.findViewById(R.id.btn_reOrder);
        }
    }
}
