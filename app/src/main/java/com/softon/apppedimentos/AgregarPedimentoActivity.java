package com.softon.apppedimentos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.softon.apppedimentos.databinding.ActivityAgregarPedimentoBinding;
import com.softon.apppedimentos.databinding.ActivityPedimentosBinding;
import com.softon.apppedimentos.modelos.Sucursal;

import java.util.ArrayList;
import java.util.List;

public class AgregarPedimentoActivity extends AppCompatActivity {

    ActivityAgregarPedimentoBinding binding;
    Spinner spSucursales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgregarPedimentoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        spSucursales = binding.spSucursal;

        listaSucursales();
    }

    public void listaSucursales(){

        List<Sucursal> sucursalList = new ArrayList<>();

        sucursalList.add(new Sucursal("Veracruz",1));
        sucursalList.add(new Sucursal("Mexico",1));
        sucursalList.add(new Sucursal("Manzanillo",1));
        ArrayAdapter<Sucursal> adaptador;
        adaptador = new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_item,sucursalList);
        spSucursales.setAdapter(adaptador);

    }

}