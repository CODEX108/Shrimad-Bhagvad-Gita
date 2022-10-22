package com.example.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText etName,etPassword,etPhone,etEmail;
    ProgressBar prgbar;
    Button button3;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etName = findViewById(R.id.etName);
        etPassword=findViewById(R.id.etPassword);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        prgbar = (ProgressBar)findViewById(R.id.progressBar);
        button3 = findViewById(R.id.button3);


        mAuth = FirebaseAuth.getInstance();
    }

    String txtName,txtPass,txtPh,txtEmail;
    public void signupBtnClicked(View view){

        txtName =  etName.getText().toString();
         txtPass =  etPassword.getText().toString();
        txtPh =  etPhone.getText().toString();
        txtEmail =  etEmail.getText().toString();

        if(txtName.isEmpty()){
            etName.setError("Please enter name");
            etName.requestFocus();
        }

        if(txtPass.isEmpty()|| txtPass.length()<5){
            etPassword.setError("Please a valid password");
            etPassword.requestFocus();
        }

        if(txtPh.isEmpty()){
            etPhone.setError("Please enter phone number");
            etPhone.requestFocus();
        }
        if(txtEmail.isEmpty()){
            etEmail.setError("Please enter email id");
            etEmail.requestFocus();
        }
        prgbar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(txtEmail,txtPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    //model class to store info of all the clients
                    ModelUser u1 = new ModelUser(txtName,txtPass,txtPh,txtEmail);
                    //taking instance of real time database

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()) //adding child
                            .setValue(u1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isComplete()){
                                Toast.makeText(SignUp.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                prgbar.setVisibility(View.GONE);

                            }
                            else{
                                Toast.makeText(SignUp.this, "User registration failed!", Toast.LENGTH_SHORT).show();
                                prgbar.setVisibility(View.GONE);
                            }

                        }
                    });

                }
                else {
                    Toast.makeText(SignUp.this, "User registration failed!", Toast.LENGTH_SHORT).show();
                    prgbar.setVisibility(View.GONE);
                }
            }
        });
    }
}