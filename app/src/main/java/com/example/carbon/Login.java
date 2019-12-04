package com.example.carbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.LoginFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
     private Button login;
     private EditText Email;
     private EditText Pass;
     FirebaseAuth auth;
    private ProgressDialog dial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        login=(Button)findViewById(R.id.log);
        dial=new ProgressDialog(this);
        Email=(EditText)findViewById(R.id.email);
        Pass=(EditText)findViewById(R.id.pass);
        login.setOnClickListener(this);
    }
    public void UserLogin()
    {
        dial.show();
        String mail=Email.getText().toString().trim();
        String password=Pass.getText().toString().trim();
        auth.signInWithEmailAndPassword(mail,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            dial.dismiss();
                            //Toast.makeText(Login.this,"Successful Login",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this,welcomepage.class));
                        }
                        else {
                            dial.dismiss();
                            Toast.makeText(Login.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }
    @Override
    public void onClick(View v) {
        if(v==login)
        {
            UserLogin();
        }

    }
}
