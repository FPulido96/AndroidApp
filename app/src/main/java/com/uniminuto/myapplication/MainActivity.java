package com.uniminuto.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button mButtonUser;
    Button mButtonDelivery;

    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButtonUser= findViewById(R.id.btnUser);
        mButtonDelivery= findViewById(R.id.btnDelivery);
        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPref.edit();

        mButtonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("user", "client");
                editor.apply();
                goToSelectAuth();
            }
        });
        mButtonDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString( "users", "delivery" );
                editor.apply();
                goToSelectAuth();
            }
        });
    }

    private void goToSelectAuth() {
        Intent intent = new Intent(MainActivity.this, com.uniminuto.myapplication.SelectOptionAuthActivity.class);
        startActivity(intent);
    }
}