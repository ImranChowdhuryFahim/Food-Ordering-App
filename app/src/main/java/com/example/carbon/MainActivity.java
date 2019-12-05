package com.example.carbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
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
    private Button Next;
    private EditText number;
    private FirebaseAuth auth;
    private ProgressDialog dial;
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
        Next=(Button) findViewById(R.id.next);
        number=(EditText)findViewById(R.id.phone);
        Next.setOnClickListener(this);


    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    public void next()
    {
        final String Num="+88"+number.getText().toString().trim();
        if(TextUtils.isEmpty(Num))
        {
            Toast.makeText(this,"Please Enter Valid Phone Number",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isNetworkConnected())
        {
            Toast.makeText(this,"Please Check Your Internet Connection",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent=new Intent(MainActivity.this,Login.class);
        intent.putExtra("pn",Num);
        startActivity(intent);

    }

    public void UserLogin()
    {
       startActivity(new Intent(this,Login.class));
    }

    @Override
    public void onClick(View v) {
        if(v==Next)
        {
            next();

        }


    }
}
