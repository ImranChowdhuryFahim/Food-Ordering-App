package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Regactivity extends AppCompatActivity implements View.OnClickListener {
    private EditText Fname;
    private EditText Lname;
    private Button Register;
    private RadioButton Male;
    private  RadioButton Female;
    private EditText Pass;
    private EditText Cpass;
    FirebaseAuth auth;
    FirebaseUser user;
    private RadioGroup rdg;
    private ProgressDialog dial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regactivity);
        Fname=(EditText)findViewById(R.id.fname);
        Lname=(EditText)findViewById(R.id.lname);
        Register=(Button)findViewById(R.id.reg);
        Male=(RadioButton)findViewById(R.id.male);
        Female=(RadioButton)findViewById(R.id.female);
        Pass=(EditText)findViewById(R.id.pass);
        Cpass=(EditText)findViewById(R.id.cpass);
        rdg=(RadioGroup)findViewById(R.id.radioGroup1);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        dial=new ProgressDialog(this);
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
        String Name=Fname.getText().toString().trim()+" "+Lname.getText().toString().trim();
        int selectedId = rdg.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        String pass=Pass.getText().toString().trim();
        String cpass=Cpass.getText().toString().trim();
        if(TextUtils.isEmpty((Fname.getText())))
        {
            Fname.setError("First name can't be null");
            return;
        }
        if(TextUtils.isEmpty(Lname.getText()))
        {
            Lname.setError("Last name can't be null");
            return;
        }
        if(TextUtils.isEmpty(pass))
        {
            Pass.setError("Password can't be null");
            return;
        }
        if(!pass.equals(cpass))
        {
            Cpass.setError("Password didn't match");
            return;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user");
        User u=new User(Name,radioButton.getText().toString(),user.getPhoneNumber(),pass);
       //Toast.makeText(Regactivity.this,u.getGender(),Toast.LENGTH_SHORT).show();
        myRef.child(user.getUid()).setValue(u);
        Intent intent=new Intent(Regactivity.this,home.class);
        startActivity(intent);
        finish();
      //startActivity(new Intent(Regactivity.this, home.class));

    }

    @Override
    public void onClick(View v) {
        if(v==Register)
        {
            UserRegistration();
        }

    }
}
