package com.example.carbon.ui.gallery;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbon.Cart;
import com.example.carbon.Database.Database;
import com.example.carbon.Model.cart;
import com.example.carbon.R;
import com.example.carbon.ViewHolder.CartAdapter;
import com.example.carbon.orderFormat;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GalleryFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    TextView Total;
    Button Order;
    List<cart> Cart1=new ArrayList<>();
    CartAdapter adapter;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        //startActivity(new Intent(getContext(), Cart.class));
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("order request");
        recyclerView=(RecyclerView)root.findViewById(R.id.listcart);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        Total=(TextView)root.findViewById(R.id.total);
        Order=(Button)root.findViewById(R.id.order);
        loadCart();
//        final TextView textView = root.findViewById(R.id.text_gallery);
//        Button bt=root.findViewById(R.id.order);
//        final EditText food=root.findViewById(R.id.food);
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                builder.setTitle("One more step");
//                builder.setIcon(R.drawable.ic_shopping_cart_black_24dp);
//                LayoutInflater inflater1=requireActivity().getLayoutInflater();
//                builder.setView(inflater1.inflate(R.layout.order_popup,null)).
//                        setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        });
//                builder.create();
//                builder.show();
//                Random rand=new Random();
//                int a=rand.nextInt(100000);
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReference("order request");
//                orderFormat ord=new orderFormat(String.valueOf(a),"1",food.getText().toString().trim());
//                myRef.child(String.valueOf(a)).setValue(ord);
//            }
//        });

        return root;
    }
    public void loadCart() {
//        String[] g=new String[2];
//        Cart1=new Database(getContext()).getCarts();
//        adapter=new CartAdapter(Cart1,);
//        recyclerView.setAdapter(adapter);
//        int t=0;
//        for(cart a:Cart) {
//            g = a.getPrice().split("T");
//            t += (Integer.parseInt(g[0]))*(Integer.parseInt(a.getQuantity()));
//        }
//        Total.setText(String.valueOf(t)+"Tk");
    }
}