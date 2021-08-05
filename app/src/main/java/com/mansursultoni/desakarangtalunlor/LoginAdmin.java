package com.mansursultoni.desakarangtalunlor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginAdmin extends AppCompatActivity {

    TextView tvDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        tvDaftar = findViewById(R.id.tvDaftar);
        tvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAdmin.this, RegisterAdmin.class);
                startActivity(intent);
            }
        });

    }
}