package com.example.carbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class welcomepage extends AppCompatActivity implements View.OnClickListener {
    private TextView text;
    private Button Logout;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference database;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database= FirebaseDatabase.getInstance().getReference("user");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name=dataSnapshot.child(user.getUid().toString()).child("name").getValue().toString();
                text.setText("Welcome "+name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //Toast.makeText(welcomepage.this,name,Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);
        text=(TextView)findViewById(R.id.text);
        Logout=(Button)findViewById(R.id.logout);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        Logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==Logout)
        {
            FirebaseAuth.getInstance().signOut();
            Intent intent=new Intent(this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }
}
