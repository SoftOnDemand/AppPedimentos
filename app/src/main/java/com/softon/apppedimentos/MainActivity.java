package com.softon.apppedimentos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    public void login(View view){
        Intent pedimentos = new Intent(getApplicationContext(), PedimentosActivity.class);
        startActivity(pedimentos);
    }

}