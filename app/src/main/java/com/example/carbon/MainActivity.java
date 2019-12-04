package com.example.carbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button register;
    private EditText Email;
    private EditText Pass;
    private FirebaseAuth auth;
    private ProgressDialog dial;
    private TextView Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        if(user!=null)
        {
            startActivity(new Intent(this,welcomepage.class));
        }
        super.onCreate(savedInstanceState);
        dial=new ProgressDialog(this);
        setContentView(R.layout.activity_main);
        register=(Button) findViewById(R.id.reg);
        Email=(EditText)findViewById(R.id.email);
        Pass=(EditText)findViewById(R.id.pass);
        Login=(TextView)findViewById(R.id.login);
        register.setOnClickListener(this);
        Login.setOnClickListener(this);


    }

    public void UserRegistration()
    {
        final String mail=Email.getText().toString().trim();
        final String pass=Pass.getText().toString().trim();
        if(TextUtils.isEmpty(mail))
        {
            Toast.makeText(this,"Please Enter Email Address",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
        }
        dial.setMessage("Registerring ..");
        dial.show();
        auth.createUserWithEmailAndPassword(mail,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                            dial.dismiss();
                            auth.signInWithEmailAndPassword(mail,pass)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful())
                                            {

                                                //Toast.makeText(Login.this,"Successful Login",Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(MainActivity.this,welcomepage.class));
                                            }
                                            else {
                                                dial.dismiss();
                                                Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                                            }

                                        }
                                    });

                        }
                        else {
                            Toast.makeText(MainActivity.this,"Failed to Register User", Toast.LENGTH_SHORT).show();
                            dial.dismiss();
                        }
                    }

                });

    }

    public void UserLogin()
    {
       startActivity(new Intent(this,Login.class));
    }

    @Override
    public void onClick(View v) {
        if(v==register)
        {
            UserRegistration();

        }
        if(v==Login)
        {
            UserLogin();
        }

    }
}
