package com.mansursultoni.desakarangtalunlor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterAdmin extends AppCompatActivity {

    EditText etEmail, etNamaLengkap, etNomorHp, etTanggalLahir, etAlamat, etPassword;
    Button btBuatAkun;
    boolean valid = true;
    //FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

       // fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etNamaLengkap = findViewById(R.id.etNamaLengkap);
        etNomorHp = findViewById(R.id.etNomorHp);
        etTanggalLahir = findViewById(R.id.etTanggalLahir);
        etAlamat = findViewById(R.id.etAlamat);
        etPassword = findViewById(R.id.etPassword);

        checkField(etEmail);
        checkField(etNamaLengkap);
        checkField(etNomorHp);
        checkField(etTanggalLahir);
        checkField(etAlamat);
        checkField(etPassword);


        btBuatAkun = findViewById(R.id.btBuatAkun);
        btBuatAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

            }
        });

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