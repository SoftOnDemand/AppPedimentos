package com.softon.apppedimentos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.material.snackbar.Snackbar;
import com.softon.apppedimentos.conexionWS.Constantes;
import com.softon.apppedimentos.conexionWS.VolleySingleton;
import com.softon.apppedimentos.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    EditText edtUsuario,edtContrasena;
    int idUser;
    String nombreUser;
    ProgressDialog pdActualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        edtUsuario = binding.edtUsuario;
        edtContrasena = binding.edtContrasena;
        pdActualizar = new ProgressDialog(this);

    }

    public void login(View view){

        pdActualizar.setTitle("Iniciando Sesion");
        pdActualizar.setMessage("Por favor espere un momento...");
        pdActualizar.setCancelable(true);
        pdActualizar.setIndeterminate(false);
        pdActualizar.show();

        if(edtUsuario.getText().toString().isEmpty() || edtContrasena.getText().toString().isEmpty()){
            Snackbar.make(view,"Usuario o Contraseña vacios",Snackbar.LENGTH_LONG).show();
            pdActualizar.dismiss();
            return;
        }
        VolleySingleton.getInstance(this.getApplicationContext())
                .addToRequestQueue(
                        new JsonArrayRequest(
                                Request.Method.POST,
                                Constantes.login,
                                null,
                                response -> {
                                    pdActualizar.dismiss();
                                    System.out.println(response.toString());
                                    JSONObject obj;

                                    try {
                                        obj = response.getJSONObject(0);
                                        idUser = obj.getInt("id");
                                        nombreUser = obj.getString("name");
                                        almacenarUsuario();
                                        Intent pedimentos = new Intent(getApplicationContext(), PedimentosActivity.class);
                                        startActivity(pedimentos);
                                    } catch (JSONException e) {
                                        Log.d("LOGIN ERROR",e.toString());
                                    }

                                },
                                error -> {
                                    pdActualizar.dismiss();
                                    System.out.println(error.toString());
                                    Snackbar.make(view,error.toString(),Snackbar.LENGTH_LONG).show();
                                }
                        ){
                            @Override
                            public byte[] getBody() {
                                HashMap<String,String> params = new HashMap<>();
                                params.put("correo",edtUsuario.getText().toString());
                                params.put("contrasena",edtContrasena.getText().toString());
                                return new JSONObject(params).toString().getBytes();
                            }

                            @Override
                            public String getBodyContentType() {
                                return "application/json";
                            }
                        }
                );

    }

    public void almacenarUsuario(){
        SharedPreferences preferencias = this.getSharedPreferences("usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();

        editor.putInt("IdUsuario",idUser);
        editor.putString("NombreUsuario",nombreUser);

        editor.apply();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferencias = this.getSharedPreferences("usuario", Context.MODE_PRIVATE);
        idUser = preferencias.getInt("IdUsuario",0);
        if(idUser != 0){
            Intent pedimentos = new Intent(getApplicationContext(), PedimentosActivity.class);
            startActivity(pedimentos);
        }

    }
}