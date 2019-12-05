package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class welcomepage extends AppCompatActivity implements View.OnClickListener {
    private TextView text;
    private Button Logout;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);
        text=(TextView)findViewById(R.id.text);
        Logout=(Button)findViewById(R.id.logout);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        text.setText("Welcome "+user.getPhoneNumber());
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
