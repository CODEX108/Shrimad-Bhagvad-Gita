package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class Splash_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextView t1= findViewById(R.id.tileSplash);
        TextView t2 = findViewById(R.id.smallTitle);
        t1.animate().translationX(1000).setDuration(1000).setStartDelay(3000);
        t2.animate().translationX(1000).setDuration(1000).setStartDelay(3000);

        Thread thread = new Thread(){
            public  void run(){
                try{
                    Thread.sleep(4000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent =new Intent(Splash_Activity.this,Welcome.class);
                    startActivity(intent);
                    finish();//when the user clicks back it won't take him back to the splash screen,it flushes it from the memory
                }
            }
        };
        thread.start();
    }
}