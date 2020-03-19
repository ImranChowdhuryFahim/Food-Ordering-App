package com.example.carbon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carbon.Database.Database;
import com.example.carbon.Model.cart;
import com.example.carbon.ViewHolder.CartAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.dmoral.toasty.Toasty;

public class Cart extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    public TextView Total;
    Button Order;
    List<cart> Cart=new ArrayList<>();
    CartAdapter adapter;
    String Adress;
    String total=null;
    private FirebaseUser user;
    private FirebaseAuth auth;
    int o=0;
    Database mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Cart");
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("order request");
        recyclerView=(RecyclerView)findViewById(R.id.listcart);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Total=(TextView)findViewById(R.id.total);
        Order=(Button) findViewById(R.id.order);
        loadCart();
        Order.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (home.or>0) {
                    if(!isNetworkConnected())
                    {
                        Toasty.warning(Cart.this,"Please Check Your Internet Connection",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(Cart.this);
                    builder.setTitle("One more step");
                    builder.setIcon(R.drawable.ic_shopping_cart_black_24dp);
                    builder.setMessage("Enter Your Location");
                    final EditText adress = new EditText(Cart.this);
                    adress.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT
                    );
                    adress.setLayoutParams(layoutParams);
                    builder.setView(adress);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            String k = String.valueOf(System.currentTimeMillis());
//                            List<String> s=new ArrayList<String>();
//                            s.add("0");
//                            orderFormat ord = new orderFormat(k, user.getPhoneNumber().toString(), adress.getText().toString(), Total.getText().toString(), Cart, s);
//                            databaseReference.child(k).setValue(ord);
//                            new Database(getApplicationContext()).cleanCart();
//                            Toasty.success(Cart.this, "Your Order Have been Placed",
//                                    Toast.LENGTH_SHORT, true).show();
//                            home.or=0;
//                            //Toast.makeText(getBaseContext(),"Your Order Have been Placed",Toast.LENGTH_SHORT).show();
//                            loadCart();
                        }
                    });
                    builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            dialog.dismiss();
                            loadCart();
                            return;

                        }
                    });
                    builder.create();
                    //builder.show();
                    final AlertDialog dialog=builder.create();
                    dialog.show();
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(TextUtils.isEmpty(adress.getText()))
                            {
                                adress.setError("Address can't be null");
                                return;
                            }
                            String k = String.valueOf(System.currentTimeMillis());
                            List<String> s=new ArrayList<String>();
                            s.add("0");
                            orderFormat ord = new orderFormat(k, user.getPhoneNumber().toString(), adress.getText().toString(), Total.getText().toString(), Cart, s);
                            databaseReference.child(k).setValue(ord);
                            new Database(getApplicationContext()).cleanCart();
                            Toasty.success(Cart.this, "Your Order Have been Placed",
                                    Toast.LENGTH_SHORT, true).show();
                            home.or=0;
                            //Toast.makeText(getBaseContext(),"Your Order Have been Placed",Toast.LENGTH_SHORT).show();
                            loadCart();
                            dialog.dismiss();

                        }
                    });

                }
                else {
                    Toasty.warning(Cart.this,"There is no food to order",Toast.LENGTH_SHORT,true).show();
                }
            }
        });

    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public void loadCart() {
        String[] g=new String[2];
        Cart=new Database(this).getCarts();
        adapter=new CartAdapter(Cart,this);
        recyclerView.setAdapter(adapter);
        int t=0;
        for(cart a:Cart) {
            g = a.getPrice().split("T");
            t += (Integer.parseInt(g[0]))*(Integer.parseInt(a.getQuantity()));
        }
        Total.setText(String.valueOf(t)+"Tk");
    }


}
