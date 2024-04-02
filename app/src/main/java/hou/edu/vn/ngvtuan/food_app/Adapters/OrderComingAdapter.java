package hou.edu.vn.ngvtuan.food_app.Adapters;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.BillModel;

public class OrderComingAdapter extends RecyclerView.Adapter<OrderComingAdapter.ViewHolder> {
    private final ArrayList<BillModel> billModels;
    private DatabaseReference databaseReference;
    private HashMap<Integer, Boolean> timerFinishedMap;
    private RecyclerView recyclerView;

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public OrderComingAdapter(ArrayList<BillModel> billModels) {
        this.billModels = billModels;
        timerFinishedMap = new HashMap<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ordercoming, parent, false));
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.idBill.setText(billModels.get(position).getBillKey());
        holder.dateOrder.setText(billModels.get(position).getDateBill().substring(0,Math.min(billModels.get(position).getDateBill().length(),10)));
        holder.quantity.setText(billModels.get(position).getQuantity());
        holder.totalPrice.setText(billModels.get(position).getTotalPrice());
        holder.status.setText(R.string.ordercoming);

        FoodOrderComingAdapter foodOrderComingAdapter = new FoodOrderComingAdapter(billModels.get(position).getCartModels());
        holder.listFood.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(),RecyclerView.HORIZONTAL,false));
        holder.listFood.setAdapter(foodOrderComingAdapter);
        foodOrderComingAdapter.notifyDataSetChanged();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Bill");

        if (timerFinishedMap.containsKey(position) && Boolean.TRUE.equals(timerFinishedMap.get(position))) {
            holder.btnCancel.setVisibility(View.GONE);
            holder.status.setVisibility(View.VISIBLE);
        } else {
            // Start the timer only if it's not finished
            startCountDownTimer(holder, position);
        }

        holder.btnCancel.setOnClickListener(v -> {
            holder.countDownTimer.cancel();
            billModels.get(position).setStatusBill("Cancel");
            Map<String,Object> map = new HashMap<>();

            map.put("statusBill","Đã huỷ");
            databaseReference.child(billModels.get(position).getBillKey()).updateChildren(map);

        });
    }

    public void saveTimerState() {
        for (int i = 0; i < billModels.size(); i++) {
            ViewHolder holder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
            if (holder != null && holder.countDownTimer != null) {
                holder.countDownTimer.cancel();
                timerFinishedMap.put(i, holder.timerFinished);
            }
        }
    }

    public void restoreTimerState() {
        for (Map.Entry<Integer, Boolean> entry : timerFinishedMap.entrySet()) {
            if (!entry.getValue()) {
                ViewHolder holder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(entry.getKey());
                if (holder != null) {
                    startCountDownTimer(holder, entry.getKey());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return billModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idBill,dateOrder,totalPrice,quantity,status;
        Button btnCancel;
        RecyclerView listFood;
        CountDownTimer countDownTimer;
        boolean timerFinished;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idBill = itemView.findViewById(R.id.item_coming_idBill);
            dateOrder = itemView.findViewById(R.id.item_coming_dateOfBill);
            totalPrice = itemView.findViewById(R.id.itemBill_totalPrice);
            quantity = itemView.findViewById(R.id.item_bill_quantity);
            status = itemView.findViewById(R.id.item_bill_status);
            btnCancel = itemView.findViewById(R.id.btn_cancel_Order);
            listFood = itemView.findViewById(R.id.rec_hor_listFoodOrder);
        }
    }

    private void startCountDownTimer(ViewHolder holder, int position) {
        holder.countDownTimer = new CountDownTimer(10000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                holder.btnCancel.setText("Huỷ (" + millisUntilFinished / 1000 + ")");
            }

            @Override
            public void onFinish() {
                holder.btnCancel.setVisibility(View.GONE);
                holder.status.setVisibility(View.VISIBLE);

                // Update status in database
                billModels.get(position).setStatusBill("Coming");
                Map<String, Object> map = new HashMap<>();
                map.put("statusBill", "Coming");
                databaseReference.child(billModels.get(position).getBillKey()).updateChildren(map);

                // Mark timer as finished for this position
                timerFinishedMap.put(position, true);
                holder.timerFinished = true;
            }
        };
        holder.countDownTimer.start();
    }
}
