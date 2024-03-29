package com.example.carbon.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbon.R;

public class CatagoryViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView price;
    public Button cart;
    public ImageView imageView;
    public TextView avl,dtsa;
    public CatagoryViewHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.foodname);
        price = (TextView) itemView.findViewById(R.id.price);
        cart=(Button)itemView.findViewById(R.id.cart);
        imageView=(ImageView)itemView.findViewById(R.id.img);
        avl=(TextView)itemView.findViewById(R.id.avl);
        dtsa=(TextView)itemView.findViewById(R.id.fooddtails);
    }
}
