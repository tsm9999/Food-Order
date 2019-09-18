package com.example.bottombar_navigation_with_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myadapter extends RecyclerView.Adapter<myadapter.ViewHolder> {


    private List<ListItem> listitem;
    private Context context;
    public myadapter(List<ListItem> listitemList, Context context) {
        this.listitem = listitemList;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.orders,parent,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem listitems =listitem.get(position);
        holder.textitems.setText(listitems.getItems());
        holder.textorderid.setText(listitems.getOrder_id());
        holder.textpay_method.setText(listitems.getPayment_method());
        holder.tot_cost.setText(String.valueOf(listitems.getTotal_cost()));
        holder.username.setText(listitems.getUsername());
        holder.status.setText(listitems.getStatus());
    }
    @Override
    public int getItemCount() {
        return listitem.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textitems;
        public TextView textorderid;
        public TextView textpay_method;
        public TextView tot_cost;
        public TextView username;
        public TextView status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username= (TextView) itemView.findViewById(R.id.textView1);
            textorderid= (TextView) itemView.findViewById(R.id.textView2);
            textitems= (TextView) itemView.findViewById(R.id.textView3);
            textpay_method= (TextView) itemView.findViewById(R.id.textView4);
            tot_cost= (TextView) itemView.findViewById(R.id.textView5);
            status= (TextView) itemView.findViewById(R.id.textView6);
        }
    }
}
