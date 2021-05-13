package com.example.buihoangdat_bai2;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.OrderViewHolder> {
    private List<Order> list;
    private Activity activity;
    public RecyclerViewAdapter(Activity activity) {
        list=new ArrayList<>();
        this.activity = activity;
    }
    public void setOrder(List<Order> list){
        this.list=list;
    }
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.card_view,parent,false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order s = list.get(position);
        holder.txtName.setText(s.getName());
        holder.txtDate.setText("Date: "+s.getDate());
        holder.txtAmount.setText("Amount: "+s.getAmount());
        holder.txtPrice.setText("Price:"+s.getPrice());


        Activity activity = this.activity;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Edit_Delete_Activity.class);
                intent.putExtra("id", s.getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        else
            return 0;
    }

     class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtDate;
        private TextView txtPrice;
        private TextView txtAmount;

        public OrderViewHolder(@NonNull View v) {
            super(v);
            txtName = v.findViewById(R.id.txtName);
            txtDate = v.findViewById(R.id.txtDate);
            txtPrice = v.findViewById(R.id.txtPrice);
            txtAmount = v.findViewById(R.id.txtAmount);
        }

    }

}
