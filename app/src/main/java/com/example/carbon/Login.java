package com.example.carbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.LoginFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goodiebag.pinview.Pinview;
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

import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button Next;
    private Pinview pass;
    FirebaseAuth auth;
    private ProgressDialog dial;
    private static String verid=null;
    int flag=0;
    public String rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        Next=(Button)findViewById(R.id.next);
        dial=new ProgressDialog(this);
        pass=(Pinview) findViewById(R.id.otp);
        Next.setOnClickListener(this);
        next();
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    public void next()
    {

        if(flag==1)
        {
            if(pass.getValue().length()>=1)
            {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verid, pass.getValue().toString());
                signInWithPhoneAuthCredential(credential);
            }
        }

        flag=1;
        if(!isNetworkConnected())
        {
            Toast.makeText(this,"Please Check Your Internet Connection",Toast.LENGTH_SHORT).show();
            return;
        }
        String phoneNumber=getIntent().getStringExtra("pn");
        rs=getIntent().getStringExtra("rs");
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
    {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {

            // Toast.makeText(Login.this,"sent",Toast.LENGTH_SHORT).show();

            if(credential.getSmsCode()!=null) {
                pass.setValue(String.valueOf(credential.getSmsCode()));
                dial.setMessage("Please Wait...");
                dial.show();
                signInWithPhoneAuthCredential(credential);
            }
            else {
                dial.setMessage("Verifying...");
                dial.show();
                signInWithPhoneAuthCredential(credential);
            }


        }

        @Override
        public void onVerificationFailed(FirebaseException e) {


            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                Toast.makeText(Login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                // ...
            } else if (e instanceof FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Toast.makeText(Login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                // ...
            }


        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            verid=verificationId;

            //Toast.makeText(Login.this,"pataisi",Toast.LENGTH_SHORT).show();

        }
    };
    private void signInWithPhoneAuthCredential(final PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            //FirebaseUser user = task.getResult().getUser();

                            if(rs.equals("123")){
                                dial.dismiss();
                                Intent intent=new Intent(Login.this,home.class);
                                finishAffinity();
                                startActivity(intent);
                            }
                            else {
                                dial.dismiss();
                                //Toast.makeText(Login.this,rs,Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, Regactivity.class);
                                startActivity(intent);
                            }
                            //startActivity(new Intent(Login.this, Regactivity.class));
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                dial.dismiss();
                                // The verification code entered was invalid
                                Toast.makeText(Login.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
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

    }
}


