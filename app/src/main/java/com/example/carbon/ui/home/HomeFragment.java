package com.example.carbon.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.andremion.counterfab.CounterFab;
import com.example.carbon.Menu;
import com.example.carbon.R;
import com.example.carbon.foodlist;
import com.example.carbon.setfoodlist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {
    ImageView ben,ind,chin,drk,fast,des,setmenu,kabab,allitm;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    TextView post;
    public CounterFab fab;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        fab=(CounterFab)getActivity().findViewById(R.id.counter_fab);
        fab.setVisibility(View.VISIBLE);
        ben= (ImageView) root.findViewById(R.id.ben1);
        ind= (ImageView) root.findViewById(R.id.ind1);
        chin= (ImageView) root.findViewById(R.id.chin2);
        drk= (ImageView) root.findViewById(R.id.drn1);
        fast= (ImageView) root.findViewById(R.id.fst1);
        des= (ImageView) root.findViewById(R.id.des1);
        setmenu=(ImageView)root.findViewById(R.id.setmenu);
        kabab=(ImageView)root.findViewById(R.id.kebab);
        post=(TextView)root.findViewById(R.id.movingtext);
        database= FirebaseDatabase.getInstance();
        databaseReference=database.getReference("offer post");
        post.setSelected(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String p=dataSnapshot.child("1").getValue().toString();
                post.setText(p);
                post.setSelected(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        allitm = root.findViewById(R.id.alitm);

        // all button
        allitm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signin = new Intent(getContext(),foodlist.class);
                signin.putExtra("key","All");
                startActivity(signin);
            }
        });

        ben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(getContext(),foodlist.class);
                signin.putExtra("key","Bengali");
                startActivity(signin);

            }
        });
        ind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(getContext(),foodlist.class);
                signin.putExtra("key","Indian");
                startActivity(signin);

            }
        });
        chin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(getContext(),foodlist.class);
                signin.putExtra("key","Chinese");
                startActivity(signin);

            }
        });
        drk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(getContext(),foodlist.class);
                signin.putExtra("key","Drinks");
                startActivity(signin);

            }
        });
        fast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(getContext(),foodlist.class);
                signin.putExtra("key","Fast food");
                startActivity(signin);

            }
        });
        des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(getContext(),foodlist.class);
                signin.putExtra("key","Dessert");
                startActivity(signin);

            }
        });
        setmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(getContext(),setfoodlist.class);
                signin.putExtra("key","Setmenu");
                startActivity(signin);
            }
        });
        kabab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(getContext(),foodlist.class);
                signin.putExtra("key","Kabab");
                startActivity(signin);
            }
        });
        return root;
    }
}