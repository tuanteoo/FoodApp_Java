package hou.edu.vn.ngvtuan.food_app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.BillModel;

public class HistoryOrderAdapter extends FirebaseRecyclerAdapter<BillModel, HistoryOrderAdapter.ViewHolder> {

    public HistoryOrderAdapter(@NonNull FirebaseRecyclerOptions<BillModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull BillModel model) {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_hisorder_item,parent,false);

        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idBill,dateBill,quantity,totalPrice,status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
