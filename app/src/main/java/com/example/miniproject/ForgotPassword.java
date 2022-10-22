package com.example.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    EditText editEmail;
    Button button4;
    ProgressBar pgBar3;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        editEmail = findViewById(R.id.editEmail);
        button4 = findViewById(R.id.button4);
        pgBar3 = (ProgressBar) findViewById(R.id.pgBar3);
        mAuth = FirebaseAuth.getInstance();

    }

    public void forgotPassBtnPress(View view){
         resetPass();
    }


    String txtEmail;
    private void resetPass(){

        txtEmail = editEmail.getText().toString().trim();
        if(!Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches()){
            editEmail.setError("Please enter valid email");
            editEmail.requestFocus();
        }

        pgBar3.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(txtEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pgBar3.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this,"Please enter your email id to reset password",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ForgotPassword.this,SignIn.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ForgotPassword.this,"Failed to reset the password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}