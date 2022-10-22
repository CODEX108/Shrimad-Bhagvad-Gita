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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    EditText etName1,etPassword1;
    TextView fpass,registertxt;
    ProgressBar pgBar;
    Button btnSignIn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etName1 =  findViewById(R.id.etName1);
        etPassword1 = findViewById(R.id.etPassword1);
        fpass = findViewById(R.id.fpass);
        registertxt = findViewById(R.id.registertxt);
        btnSignIn = findViewById(R.id.btnSignIn);
        pgBar = findViewById(R.id.progressBar2);

       mAuth= FirebaseAuth.getInstance();
    }

    public void txtDSignInFPclick(View view){

        Intent intent =  new Intent(this,ForgotPassword.class);
        startActivity(intent);
    }

    public void txtDSignInRegisterclick(View view){

        Intent intent =  new Intent(this,SignUp.class);
        startActivity(intent);
        finish();//removes the activity from the memory
    }

    String userName,password;
    public void btnSignInClicked (View view){

         userName = etName1.getText().toString().trim();
        password = etPassword1.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(userName).matches()){
            etName1.setError("Please enter valid email id");
            etName1.requestFocus();
        }
        if(etPassword1.length()<5){
            etPassword1.setError("Please enter vailed password containing atleast 6 chracters");
            etPassword1.requestFocus();
        }
        pgBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(userName,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {
                    pgBar.setVisibility(View.GONE);
                    Toast.makeText(SignIn.this, "User successfully logged in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignIn.this, MainActivity.class));
                }
                else {
                    pgBar.setVisibility(View.GONE);
                    Toast.makeText(SignIn.this, "User failed to sign in", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

}