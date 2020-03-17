package com.example.carbon.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbon.Model.cart;
import com.example.carbon.R;
import com.example.carbon.Recyclerviewholder;
import com.example.carbon.foodlist;
import com.example.carbon.recylerorderformat;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<recylerorderformat> arrayList;
    private FirebaseRecyclerOptions<recylerorderformat> options;
    private FirebaseRecyclerAdapter<recylerorderformat, Recyclerviewholder> adapter;
    private DatabaseReference databaseReference;
    FirebaseAuth auth;
    FirebaseUser user;
    public String pnum;

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<recylerorderformat>();
        databaseReference = FirebaseDatabase.getInstance().getReference("order request");
        databaseReference.keepSynced(true);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        Query query=databaseReference.orderByChild("phoneNumber").equalTo(user.getPhoneNumber().toString());
        options = new FirebaseRecyclerOptions.Builder<recylerorderformat>().setQuery(query, recylerorderformat.class).build();
        adapter = new FirebaseRecyclerAdapter<recylerorderformat, Recyclerviewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Recyclerviewholder recyclerviewholder, int i, @NonNull final recylerorderformat recylerorderformat) {
                String msg = "";
                List<cart> ord = new ArrayList<>();
                ord = recylerorderformat.getCart();
                pnum=recylerorderformat.getPhoneNumber().toString();
                for (cart h : ord) {
                    msg += h.getName().toString() + " " + h.getQuantity().toString()+"x" + ",";
                }
                if (msg.length() > 1) {
                    msg = msg.substring(0, msg.length() - 1);
                }
                recyclerviewholder.ordkey.setText(msg);
                recyclerviewholder.ordsts.setText(recylerorderformat.getTotal());
                final List<cart> finalOrd = ord;

            }

            @NonNull
            @Override
            public Recyclerviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new Recyclerviewholder(LayoutInflater.from(getContext()).inflate(R.layout.uorderrow,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);
        return root;
    }
}