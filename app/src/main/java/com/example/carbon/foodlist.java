package com.example.carbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.andremion.counterfab.CounterFab;
import com.example.carbon.Database.Database;
import com.example.carbon.Model.cart;
import com.example.carbon.Model.food;
import com.example.carbon.ViewHolder.CatagoryViewHolder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


public class foodlist extends AppCompatActivity {
    RecyclerView recycle;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<food, CatagoryViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference reference;
    String type = null;
    Database mydb;
    androidx.appcompat.widget.Toolbar toolbar;
    Window window;
    CounterFab fab;
    private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mydb=new Database(this);
        window=getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        setContentView(R.layout.activity_foodlist);
//        ActionBar actionBar=getSupportActionBar();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //type = b.getString("key");
        //init data base
        toolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fab=(CounterFab)toolbar.findViewById(R.id.fab);
        Bundle b = getIntent().getExtras();
        if(b!=null){
            String val = b.getString("key");
            //Log.d("tag",val);
            type = val;
            getSupportActionBar().setTitle(type+" "+"Items");
        }
        mStorageRef = FirebaseStorage.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        reference= database.getReference(type);
        reference.keepSynced(true);
        recycle=this.<RecyclerView>findViewById(R.id.recycle);
        recycle.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycle.setLayoutManager(layoutManager);
        showlist();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),Cart.class));
            }
        });
        fab.setCount(mydb.numberOfRows());
        home.or=fab.getCount();
    }

    private void showlist() {
        Query firebasesearchquery=reference.orderByChild("searchname");
        FirebaseRecyclerOptions options=
                new FirebaseRecyclerOptions.Builder<food>()
                        .setQuery(firebasesearchquery, food.class).build();

        adapter = new FirebaseRecyclerAdapter<food, CatagoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CatagoryViewHolder catagoryViewHolder, int i, @NonNull final food food) {
                catagoryViewHolder.name.setText(food.getName());
                catagoryViewHolder.price.setText(food.getPrice());
                Picasso.get().load(food.getImage()).into(catagoryViewHolder.imageView);
                catagoryViewHolder.cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mydb.addCart(new cart(
                                  type,
                                  food.getName(),
                                  food.getPrice(),
                                 "1"
                        ));
                        fab.setCount(mydb.numberOfRows());
                        home.or=fab.getCount();
                        Toast.makeText(foodlist.this,"Added to Cart",Toast.LENGTH_SHORT).show();
                    }
                });
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

    private void FirebaseSearch(String srchtxt)
    {

        String query=srchtxt.toLowerCase();
        Query firebasesearchquery=reference.orderByChild("searchname").startAt(query).endAt(query + "\uf8ff");
        FirebaseRecyclerOptions options=new FirebaseRecyclerOptions.Builder<food>().setQuery(firebasesearchquery,food.class).build();
        adapter=new FirebaseRecyclerAdapter<food, CatagoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CatagoryViewHolder catagoryViewHolder, int i, @NonNull final food food) {
                catagoryViewHolder.name.setText(food.getName());
                catagoryViewHolder.price.setText(food.getPrice());
                Picasso.get().load(food.getImage()).into(catagoryViewHolder.imageView);
                catagoryViewHolder.cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mydb.addCart(new cart(
                                type,
                                food.getName(),
                                food.getPrice(),
                                "1"
                        ));
                        fab.setCount(mydb.numberOfRows());
                        home.or=fab.getCount();
                        Toast.makeText(foodlist.this,"Added to Cart",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @NonNull
            @Override
            public CatagoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.foodlist,parent,false);
                CatagoryViewHolder catagoryViewHolder=new CatagoryViewHolder(view);
                return catagoryViewHolder;
            }
        };
        adapter.startListening();
        adapter.notifyDataSetChanged();
        recycle.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.search);
        androidx.appcompat.widget.SearchView searchView=(androidx.appcompat.widget.SearchView)searchViewItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                FirebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                FirebaseSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


}
