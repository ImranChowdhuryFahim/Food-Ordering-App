package com.example.carbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carbon.Model.food;
import com.example.carbon.ViewHolder.CatagoryViewHolder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class foodlist extends AppCompatActivity {
    RecyclerView recycle;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<food, CatagoryViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);
        //type = b.getString("key");
        //init data base
        String type = null;
        Bundle b = getIntent().getExtras();
        if(b!=null){
            String val = b.getString("key");
            //Log.d("tag",val);
            type = val;
        }
        database = FirebaseDatabase.getInstance();
        reference= database.getReference(type);
        reference.keepSynced(true);
        recycle=this.<RecyclerView>findViewById(R.id.recycle);
        recycle.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycle.setLayoutManager(layoutManager);
        showlist();
    }

    private void showlist() {
        FirebaseRecyclerOptions options=
                new FirebaseRecyclerOptions.Builder<food>()
                        .setQuery(reference, food.class).build();

        adapter = new FirebaseRecyclerAdapter<food, CatagoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CatagoryViewHolder catagoryViewHolder, int i, @NonNull food food) {
                catagoryViewHolder.name.setText(food.getName());
                catagoryViewHolder.price.setText(food.getPrice());
            }

            @NonNull
            @Override
            public CatagoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.foodlist,parent,false);
                return new CatagoryViewHolder(view);
            }
        };
        adapter.startListening();
        adapter.notifyDataSetChanged();
        recycle.setAdapter(adapter);

    }
}
