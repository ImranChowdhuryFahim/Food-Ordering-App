package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Regactivity extends AppCompatActivity implements View.OnClickListener {
    private EditText Fname;
    private EditText Lname;
    private Button Register;
    private RadioButton Male;
    private  RadioButton Female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regactivity);
        Fname=(EditText)findViewById(R.id.fname);
        Lname=(EditText)findViewById(R.id.lname);
        Register=(Button)findViewById(R.id.reg);
        Male=(RadioButton)findViewById(R.id.male);
        Female=(RadioButton)findViewById(R.id.female);
        Register.setOnClickListener(this);
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public void UserRegistration()
    {
        if(!isNetworkConnected())
        {
            Toast.makeText(this,"Please Check Your Internet Connection",Toast.LENGTH_SHORT).show();
            return;
        }
        String Gender=null;
        String Name=Fname.getText().toString().trim()+" "+Lname.getText().toString().trim();
        if(Male.isChecked())
        {
            Gender="Male";
        }
        else if(Female.isChecked())
        {
            Gender="Female";
        }
        Toast.makeText(Regactivity.this,Name+" "+Gender,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Regactivity.this, welcomepage.class));
    }
    @Override
    public void onClick(View v) {
        if(v==Register)
        {
            UserRegistration();
        }

    }
}
