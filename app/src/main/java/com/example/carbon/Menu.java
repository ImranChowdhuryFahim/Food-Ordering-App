package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Menu extends AppCompatActivity {
    ImageView ben,ind,chin,drk,fast,des,allitm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ben= this.<ImageView>findViewById(R.id.ben1);
        ind= this.<ImageView>findViewById(R.id.ind1);
        chin= this.<ImageView>findViewById(R.id.chin2);
        drk= this.<ImageView>findViewById(R.id.drn1);
        fast= this.<ImageView>findViewById(R.id.fst1);
        des= this.<ImageView>findViewById(R.id.des1);

        ben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(Menu.this,foodlist.class);
                signin.putExtra("key","Bengali");
                startActivity(signin);
                finish();
            }
        });
        ind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(Menu.this,foodlist.class);
                signin.putExtra("key","Indian");
                startActivity(signin);
                finish();
            }
        });
        chin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(Menu.this,foodlist.class);
                signin.putExtra("key","Chineses");
                startActivity(signin);
                finish();
            }
        });
        drk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(Menu.this,foodlist.class);
                signin.putExtra("key","Drinks");
                startActivity(signin);
                finish();
            }
        });
        fast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(Menu.this,foodlist.class);
                signin.putExtra("key","Fast food");
                startActivity(signin);
                finish();
            }
        });
        des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(Menu.this,foodlist.class);
                signin.putExtra("key","Dessert");
                startActivity(signin);
                finish();
            }
        });
    }
}
