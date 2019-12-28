package com.example.carbon.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbon.R;

public class CatagoryViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView price;
    public CatagoryViewHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.foodname);
        price = (TextView) itemView.findViewById(R.id.price);
    }
}
