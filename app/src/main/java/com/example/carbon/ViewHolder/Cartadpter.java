package com.example.carbon.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.carbon.Cart;
import com.example.carbon.Database.Database;
import com.example.carbon.Model.cart;
import com.example.carbon.R;
import com.example.carbon.ui.gallery.GalleryFragment;

import java.util.ArrayList;
import java.util.List;

class CartadpViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView item_name,item_price;
    public ElegantNumberButton ebtn;

    @Override
    public void onClick(View v) {

    }

    public CartadpViewHolder(@NonNull View itemView) {
        super(itemView);
        item_name=(TextView)itemView.findViewById(R.id.item_name);
        item_price=(TextView)itemView.findViewById(R.id.item_price);
        ebtn=(ElegantNumberButton)itemView.findViewById(R.id.numbtn);
    }
}
public class Cartadpter extends RecyclerView.Adapter<CartViewHolder> {
    private List<cart> list=new ArrayList<>();
    private GalleryFragment context;

    public Cartadpter(List<cart> list, GalleryFragment context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context.getContext());
        View itemView=inflater.inflate(R.layout.cart_row,parent,false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, final int position) {
        holder.item_name.setText(list.get(position).getName());
        holder.item_price.setText(list.get(position).getPrice());
        holder.ebtn.setNumber(list.get(position).getQuantity());
        holder.ebtn.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                cart c=list.get(position);
                c.setQuantity(String.valueOf(newValue));
                new Database(context.getContext()).updateCart(c);
                String[] g=new String[2];
                List<cart>abc=new Database(context.getContext()).getCarts();
                int t=0;
                for(cart a:abc) {
                    g = a.getPrice().split("T");
                    t += (Integer.parseInt(g[0]))*(Integer.parseInt(a.getQuantity()));
                }
                context.Total.setText(String.valueOf(t)+"Tk");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
