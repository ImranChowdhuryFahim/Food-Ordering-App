package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserLogin extends AppCompatActivity implements View.OnClickListener {
    private EditText Pass;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        Pass=(EditText)findViewById(R.id.pass);
        Login=(Button) findViewById(R.id.login);
        Login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==Login)
        {

        }

    }
}
