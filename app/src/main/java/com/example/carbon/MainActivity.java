package com.example.carbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.xml.sax.DTDHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String varicode;
    private Button Next,dlogin;
    private EditText number;
    private FirebaseAuth auth;
    private ProgressDialog dial,dial1;
    DatabaseReference database;
    ArrayList<String>s;
    String Num;
    Integer i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth=FirebaseAuth.getInstance();
        s=new ArrayList<String>(1000000);
        FirebaseUser user=auth.getCurrentUser();
        if(user!=null)
        {
            startActivity(new Intent(this,home.class));
        }
        super.onCreate(savedInstanceState);
        dial=new ProgressDialog(this);
        setContentView(R.layout.activity_main);
        Next=(Button) findViewById(R.id.next);
        dlogin=(Button)findViewById(R.id.dlogin);
        number=(EditText)findViewById(R.id.phone);
        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            Color c;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>=11)
                {
                    Next.setBackgroundColor(Color.GREEN);
                }
                else {
                    Next.setBackgroundColor(getResources().getColor(R.color.fui_bgGoogle));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Next.setOnClickListener(this);
        dlogin.setOnClickListener(this);
        database=FirebaseDatabase.getInstance().getReference("user");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int i=0;
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    String a=dataSnapshot1.child("number").getValue().toString();
                    //Toast.makeText(MainActivity.this,a,Toast.LENGTH_SHORT).show();
                    s.add(a);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    Integer f=1;
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    public void next()
    {


        Num=number.getText().toString().trim();
        int len=number.getText().length();
        Pattern pattern=Pattern.compile("^([0][1]|[+][8][8][0][1])([3-9]{1}[0-9]{8})");
        Matcher matcher=pattern.matcher(number.getText().toString());
        if(TextUtils.isEmpty(number.getText().toString()))
        {
            Toast.makeText(this,"Please Enter Your Phone Number",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!matcher.find())
        {
            Toast.makeText(this,"Please Enter a valid Phone Number",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isNetworkConnected())
        {
            Toast.makeText(this,"Please Check Your Internet Connection",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Num.length()==11)
        {
            Num="+88"+Num;
        }

        if(s.contains(Num))
        {
            //Toast.makeText(MainActivity.this,"he",Toast.LENGTH_SHORT).show();
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    Num,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    this,               // Activity (for callback binding)
                    mCallbacks);
            return;
        }

        Intent intent=new Intent(MainActivity.this,Login.class);
        intent.putExtra("pn",Num);
        startActivity(intent);

    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
    {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {

            // Toast.makeText(Login.this,"sent",Toast.LENGTH_SHORT).show();
            dial.setMessage("Verifying...");
            dial.show();
            signInWithPhoneAuthCredential(credential);

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {


            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                // ...
            } else if (e instanceof FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                // ...
            }


        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            varicode=verificationId;
            //Toast.makeText(Login.this,"pataisi",Toast.LENGTH_SHORT).show();

        }
    };
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            dial.dismiss();
                            //FirebaseUser user = task.getResult().getUser();
                            //Toast.makeText(Login.this,"successfull",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,home.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                dial.dismiss();
                                // The verification code entered was invalid
                                Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }


    @Override
    public void onClick(View v) {
        if(v==Next)
        {
           next();


        }
        else if(v==dlogin)
        {
            Intent intent=new Intent(MainActivity.this,home.class);
            intent.putExtra("key",true);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }



    }

    @Override
    protected void onPostResume() {
        if (home.close == 1)
        {
            home.close=0;
            finish();
            super.onBackPressed();
        }

        super.onPostResume();
    }
}
