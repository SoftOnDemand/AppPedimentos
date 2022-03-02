package com.softon.apppedimentos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.snackbar.Snackbar;
import com.softon.apppedimentos.conexionWS.Constantes;
import com.softon.apppedimentos.conexionWS.VolleySingleton;
import com.softon.apppedimentos.databinding.ActivityAgregarPedimentoBinding;
import com.softon.apppedimentos.databinding.ActivityPedimentosBinding;
import com.softon.apppedimentos.modelos.Pedimento;
import com.softon.apppedimentos.modelos.Sucursal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class AgregarPedimentoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    ActivityAgregarPedimentoBinding binding;
    Spinner spSucursales;
    Spinner spEstado;
    EditText edtFechaArribo,edtNoPedimento;
    TextView nomEjecutivo;
    String  URLPEDIMENTO;
    int idUser;
    int idEstado;
    int idSucursal;
    int idMetodo;
    Pedimento ped;
    ProgressDialog pdActualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgregarPedimentoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        spSucursales = binding.spSucursal;
        spSucursales.setOnItemSelectedListener(this);
        spEstado = binding.spEstado;
        spEstado.setOnItemSelectedListener(this);
        edtFechaArribo = binding.txtFechaArribo;
        edtNoPedimento = binding.txtNoPedimento;
        nomEjecutivo = binding.txtNomEjecutivo;

        SharedPreferences preferencias = this.getSharedPreferences("usuario", Context.MODE_PRIVATE);
        idUser = preferencias.getInt("IdUsuario",0);
        nomEjecutivo.setText(preferencias.getString("NombreUsuario","EJECUTIVO"));
        pdActualizar = new ProgressDialog(getApplicationContext());

        Intent intent = this.getIntent();

        ped = (Pedimento) intent.getSerializableExtra("pedimento");
        this.setTitle(R.string.crear_pedimento);
        URLPEDIMENTO = Constantes.crearPedimentos;
        idMetodo = Request.Method.POST;

        if(ped != null){
            this.setTitle(R.string.editar_pedimento);
            URLPEDIMENTO = Constantes.editarPedimentos+ped.getIdPedimento();
            idMetodo = Request.Method.PUT;
            idUser = ped.getIdEjecutivo();
            nomEjecutivo.setText(ped.getNomEjecutivo());
            edtNoPedimento.setText(ped.getNoPedimento());
            edtFechaArribo.setText(ped.getFechaArribo());
            spEstado.setSelection(ped.getEstado());
            spSucursales.setSelection(ped.getIdSucursal()-1);
        }

        listaSucursales();
    }

    public void listaSucursales(){

        List<Sucursal> sucursalList = new ArrayList<>();

        sucursalList.add(new Sucursal("Veracruz",1));
        sucursalList.add(new Sucursal("Manzanillo",2));
        sucursalList.add(new Sucursal("Altamira",3));
        ArrayAdapter<Sucursal> adaptador;
        adaptador = new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_item,sucursalList);
        spSucursales.setAdapter(adaptador);

    }

    public void guardarPedimento(View view){
        showProgress(true);
        VolleySingleton.getInstance(this.getApplicationContext())
                .addToRequestQueue(
                        new JsonObjectRequest(
                                idMetodo,
                                URLPEDIMENTO,
                                null,
                                response -> {
                                    showProgress(false);

                                    try {
                                        System.out.println(response.toString());
                                        JSONObject obj = response;
                                        Snackbar.make(view,obj.getString("descripcion"),Snackbar.LENGTH_LONG).show();
                                        Intent pedimentos = new Intent(getApplicationContext(), PedimentosActivity.class);
                                        startActivity(pedimentos);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
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
                                params.put("idUsuario",""+idUser);
                                params.put("noPedimento",edtNoPedimento.getText().toString());
                                params.put("fechaArribo",edtFechaArribo.getText().toString());
                                params.put("idSucursal",""+idSucursal);
                                params.put("estado",""+idEstado);
                                return new JSONObject(params).toString().getBytes();
                            }

                            @Override
                            public String getBodyContentType() {
                                return "application/json";
                            }
                        }
                );
    }


    protected  void showProgress(boolean show){
        try{

            if (show){
                pdActualizar.setTitle("Agregado con exito");
                pdActualizar.setMessage("Por favor espere un momento...");
                pdActualizar.setCancelable(true);
                pdActualizar.setIndeterminate(false);
                pdActualizar.show();
            }else{
                pdActualizar.dismiss();
            }

        }catch(Exception ex){
            Log.d("ERROR FIRST FRAGMENT",ex.toString());

        }

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        int indentificador = adapterView.getId();

        switch (indentificador){
            case R.id.spSucursal:
                Sucursal sucursal = (Sucursal) adapterView.getSelectedItem();
                idSucursal = sucursal.getIdSucursal();
                break;
            case R.id.spEstado:
                System.out.println(adapterView.getSelectedItem().toString());
                idEstado=0;
                if(adapterView.getSelectedItem().toString().equals("Finalizado")){
                    idEstado=1;
                }
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void MostrarDatePicker(View view){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
        edtFechaArribo.setText(anio+"/"+(mes+1)+"/"+dia);
    }
}