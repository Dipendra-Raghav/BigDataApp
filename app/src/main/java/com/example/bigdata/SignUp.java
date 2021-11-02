package com.example.bigdata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUp extends AppCompatActivity {
    TextInputLayout regName,inputEmail,regPhoneNo,inputPassword,inputCConfirmedPassword;
    Button alreadyHaveaccount,btnRegister;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        regName=findViewById(R.id.reg_name);
        inputEmail=findViewById(R.id.inputEmail);
        regPhoneNo=findViewById(R.id.reg_phoneNo);
        inputPassword=findViewById(R.id.inputPassword);
        inputCConfirmedPassword=findViewById(R.id.inputConfirmedPassword);
        btnRegister=findViewById(R.id.btnRegister);
        alreadyHaveaccount=findViewById(R.id.alreadyHaveaccount);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        if(mAuth.getCurrentUser()!=null){
//            startActivity(new Intent(getApplicationContext(),DashBoard.class));
//            finish();
//
//        }
        alreadyHaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerforAuth();
            }
        });


    }

    private void PerforAuth() {
        String email=inputEmail.getEditText().getText().toString().trim();
        String password=inputPassword.getEditText().getText().toString().trim();
        String confirmPassword=inputCConfirmedPassword.getEditText().getText().toString().trim();
        if(TextUtils.isEmpty(email))
        {
            inputEmail.setError("Required");
        }
        else if(password.isEmpty()  || password.length()<6)
        {
            inputPassword.setError("Enter Proper Password");
        }
        else if(!password.equals(confirmPassword))
        {
            inputCConfirmedPassword.setError("Password not matched");
        }else{
            progressDialog.setMessage("Please Wait While Registration....");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(SignUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(SignUp.this, "Error ! "+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }

    }

    private void sendUserToNextActivity() {
        Intent intent= new Intent(SignUp.this,DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}