package com.mansursultoni.desakarangtalunlor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginAdmin extends AppCompatActivity {

    EditText etUsername, etPassword;
    TextView tvDaftar;
    Switch active;
    Button btMasuk;
    boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        active = findViewById(R.id.active);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        checkField(etUsername);
        checkField(etPassword);


        tvDaftar = findViewById(R.id.tvDaftar);
        tvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAdmin.this, RegisterAdmin.class);
                startActivity(intent);
            }
        });

        btMasuk = findViewById(R.id.btMasuk);
        btMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("login").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String input1 = etUsername.getText().toString();
                        String input2 = etPassword.getText().toString();

                        if (snapshot.child(input1).exists()) {
                            if (snapshot.child(input1).child("password").getValue(String.class).equals(input2)) {
                                if (active.isChecked()) {
                                    if (snapshot.child(input1).child("as").getValue(String.class).equals("admin")) {
                                        preferences.setDataLogin(LoginAdmin.this, true);
                                        preferences.setDataAs(LoginAdmin.this, "admin");
                                        startActivity(new Intent(LoginAdmin.this, MenuAdmin.class));
                                    } else if (snapshot.child(input1).child("as").getValue(String.class).equals("user")){
                                        preferences.setDataLogin(LoginAdmin.this, true);
                                        preferences.setDataAs(LoginAdmin.this, "user");
                                        startActivity(new Intent(LoginAdmin.this, MenuUser.class));
                                    }
                                } else {
                                    if (snapshot.child(input1).child("as").getValue(String.class).equals("admin")) {
                                        preferences.setDataLogin(LoginAdmin.this, false);
                                        startActivity(new Intent(LoginAdmin.this, MenuAdmin.class));

                                    } else if (snapshot.child(input1).child("as").getValue(String.class).equals("user")){
                                        preferences.setDataLogin(LoginAdmin.this, false);
                                        startActivity(new Intent(LoginAdmin.this, MenuUser.class));
                                    }
                                }

                            } else {
                                Toast.makeText(LoginAdmin.this, "Kata sandi salah", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginAdmin.this, "Data belum terdaftar", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (preferences.getDataLogin(this)) {
            if (preferences.getDataAs(this).equals("admin")) {
                startActivity(new Intent(this, MenuAdmin.class));
                finish();
            } else {
                startActivity(new Intent(this, MenuUser.class));
                finish();
            }
        }
    }

    public boolean checkField(EditText textField){
        if (textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }
        return valid;
    }
}