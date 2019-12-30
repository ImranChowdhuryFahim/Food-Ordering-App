package com.example.carbon.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbon.Model.cart;
import com.example.carbon.R;

import java.util.ArrayList;
import java.util.List;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView item_name,item_price;


    @Override
    public void onClick(View v) {

    }

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        item_name=(TextView)itemView.findViewById(R.id.item_name);
        item_price=(TextView)itemView.findViewById(R.id.item_price);
    }
}
public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private List<cart> list=new ArrayList<>();
    private Context context;

    public CartAdapter(List<cart> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.cart_row,parent,false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.item_name.setText(list.get(position).getName());
        holder.item_price.setText(list.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
