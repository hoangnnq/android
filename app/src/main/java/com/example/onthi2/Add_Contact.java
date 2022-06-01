package com.example.onthi2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_Contact extends AppCompatActivity {

    EditText eSoxe,eQuangDuong;
    Button btnSua,btnQuayLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        eSoxe = findViewById(R.id.eMaSv);
        eQuangDuong = findViewById(R.id.eTenSv);
        btnSua = findViewById(R.id.add);
        btnQuayLai=findViewById(R.id.Back);

        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("MaSv",eSoxe.getText().toString());
                bundle.putString("TenSv",eQuangDuong.getText().toString());
                intent.putExtras(bundle);
                setResult(200,intent);
                finish();
            }
        });
    }

}