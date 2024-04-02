package hou.edu.vn.ngvtuan.food_app.Adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import hou.edu.vn.ngvtuan.food_app.Activities.AddAddressActivity;
import hou.edu.vn.ngvtuan.food_app.Interface.OnItemSelectedListener;
import hou.edu.vn.ngvtuan.food_app.R;
import hou.edu.vn.ngvtuan.food_app.models.DeliAddressModel;

public class DeliveryAddressAdapter extends FirebaseRecyclerAdapter<DeliAddressModel, DeliveryAddressAdapter.ViewHolder> {
    DatabaseReference databaseReference;
    private String oldSelectedItemKey = null;
    private DeliAddressModel oldSelectedItem = null;
    private final OnItemSelectedListener onItemSelectedListener;

    public void setOldSelectedItem(DeliAddressModel oldSelectedItem,String oldSelectedItemKey){
        this.oldSelectedItemKey = oldSelectedItemKey;
        this.oldSelectedItem = oldSelectedItem;
    }

    public DeliveryAddressAdapter(@NonNull FirebaseRecyclerOptions<DeliAddressModel> options,OnItemSelectedListener onItemSelectedListener) {
        super(options);
        this.onItemSelectedListener = onItemSelectedListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull DeliAddressModel model) {
        holder.deliAddress.setText(model.getDeliAddress());
        holder.username.setText(model.getUserName());
        holder.phonenumber.setText(model.getPhoneNumber());

        holder.editAddress.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AddAddressActivity.class);

            intent.putExtra("deliAddress", model.getDeliAddress());
            intent.putExtra("username", model.getUserName());
            intent.putExtra("phonenumber", model.getPhoneNumber());
            intent.putExtra("showDelete",true);
            intent.putExtra("keyAddress",model.getKeyAddress());

            v.getContext().startActivity(intent);
        });
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("DeliveryAddress").child(userId);

        holder.itemView.setOnClickListener(v -> {
           model.setSelected(true);
           updateItemOnFirebase(model.getKeyAddress(),model);

           if (oldSelectedItem != null && oldSelectedItemKey != null){
               oldSelectedItem.setSelected(false);
               updateItemOnFirebase(oldSelectedItemKey,oldSelectedItem);
           }

           oldSelectedItem = model;
           oldSelectedItemKey = model.getKeyAddress();

           onItemSelectedListener.OnItemSelected(oldSelectedItem);

            //Back to HomeFragment
            //Navigation.findNavController(v).navigate(R.id.action_nav_delivery_to_nav_home);
        });

    }

    private void updateItemOnFirebase(String key,DeliAddressModel deliAddressModel) {
        databaseReference.child(key).setValue(deliAddressModel);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deliveryaddress,parent,false);

        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView deliAddress,username,phonenumber,editAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            deliAddress = itemView.findViewById(R.id.item_deli_address);
            username = itemView.findViewById(R.id.item_deli_username);
            phonenumber = itemView.findViewById(R.id.item_deli_phoneNum);
            editAddress = itemView.findViewById(R.id.item_edit_address);
        }
    }
}
