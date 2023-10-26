package com.uniminuto.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.uniminuto.myapplication.delivery.LoginActivity;
import com.uniminuto.myapplication.delivery.RegisterActivity;

public class SelectOptionAuthActivity extends AppCompatActivity {

    Button mButtonIniciar_sesion;
    Button mButtonRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option_auth);

        mButtonIniciar_sesion = findViewById(R.id.btnIniciar_sesion);
        mButtonRegister = findViewById(R.id.btnRegistrarse);
        mButtonIniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });


    }
    public void goToLogin(){
        Intent intent = new Intent(SelectOptionAuthActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public void goToRegister(){
        Intent intent = new Intent(SelectOptionAuthActivity.this, RegisterActivity.class);
        startActivity(intent);
    }



}