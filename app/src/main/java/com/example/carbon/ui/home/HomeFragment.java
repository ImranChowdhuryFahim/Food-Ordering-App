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

import com.example.carbon.Menu;
import com.example.carbon.R;
import com.example.carbon.foodlist;

public class HomeFragment extends Fragment {
    ImageView ben,ind,chin,drk,fast,des;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        ben= (ImageView) root.findViewById(R.id.ben1);
        ind= (ImageView) root.findViewById(R.id.ind1);
        chin= (ImageView) root.findViewById(R.id.chin2);
        drk= (ImageView) root.findViewById(R.id.drn1);
        fast= (ImageView) root.findViewById(R.id.fst1);
        des= (ImageView) root.findViewById(R.id.des1);
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
        return root;
    }
}