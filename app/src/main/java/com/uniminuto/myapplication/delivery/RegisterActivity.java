package com.uniminuto.myapplication.delivery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uniminuto.myapplication.R;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    public FirebaseAuth mAuth;
    public EditText cedulai;
    public EditText celular;
    public EditText direccion;
    public EditText namei;
    public EditText correo;
    public EditText contrasenai;
    public EditText contrasenaConfirmacion;
    SharedPreferences mPref;
    DatabaseReference mDatabase;

    //variables para guardar en la base de datos

    private String cc = "";
    private String cel = "";
    private String dir = "";
    private String name = "";
    private String email = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mPref = getApplicationContext().getSharedPreferences( "typeUser", MODE_PRIVATE);
        String selectedUser = mPref.getString("user","" );
        Toast.makeText(this, "El valor seleccionado fue"  + selectedUser, Toast.LENGTH_SHORT).show();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        cedulai = findViewById(R.id.cedula);
        celular = findViewById(R.id.celular);
        direccion = findViewById(R.id.direccion);
        namei = findViewById(R.id.name);
        correo = findViewById(R.id.correo);
        contrasenai = findViewById(R.id.contrasena);
        contrasenaConfirmacion = findViewById(R.id.contraenaConfirmacion);
        SharedPreferences mPref;
    }
    @Override
    public void onStart() {
        super.onStart();
        //Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void RegisterActivity (View view){

        if(contrasenai.getText().toString().equals(contrasenaConfirmacion.getText().toString())){

            cc = cedulai.getText().toString();
            cel = celular.getText().toString();
            dir = direccion.getText().toString();
            name = namei.getText().toString();
            email = correo.getText().toString();
            password = contrasenai.getText().toString();

                if(password.length() >= 6){
                    registerUser();

                }else{
                    Toast.makeText(RegisterActivity.this, "contraseña min 6 caracteres", Toast.LENGTH_SHORT).show();

                }

        } else {
            Toast.makeText(this, "las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
        }


    }

    //metodod que hace la magia del registro en la base de datos y en la autenticacion

    private void registerUser() {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("email", email);
                    map.put("password", password);
                    map.put("cc", cc);
                    map.put("cel", cel);
                    map.put("dir", dir);

                    String id = mAuth.getCurrentUser().getUid();
                    mDatabase.child("Users").child(("user")).child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(RegisterActivity.this, MapDeliveryActivity.class));
                                finish();

                            }else{
                                Toast.makeText(RegisterActivity.this, "No se pudo crear el registro correctamente ", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                } else {
                    Toast.makeText(RegisterActivity.this, "error en el registro", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
    