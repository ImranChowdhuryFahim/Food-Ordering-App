package com.example.carbon;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Recyclerviewholder extends RecyclerView.ViewHolder {
    public TextView ordkey,ordsts;

    public Recyclerviewholder(@NonNull View itemView) {
        super(itemView);
        ordkey=(TextView)itemView.findViewById(R.id.orddtl);
        ordsts=(TextView)itemView.findViewById(R.id.totalprice);

    }
}
