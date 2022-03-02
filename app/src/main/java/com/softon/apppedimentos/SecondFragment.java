package com.softon.apppedimentos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.snackbar.Snackbar;
import com.softon.apppedimentos.conexionWS.Constantes;
import com.softon.apppedimentos.conexionWS.VolleySingleton;
import com.softon.apppedimentos.databinding.FragmentSecondBinding;
import com.softon.apppedimentos.modelos.Pedimento;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private Pedimento ped;

    TextView txtDetalleEjecutivo,
            txtDetalleNoPedimento,
            txtDetalleFechaCreacion,
            txtDetalleFechaArribo,
            txtDetalleSucursal,
            txtDetalleEstado;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);

        Intent intent = getActivity().getIntent();
        ped = (Pedimento) intent.getSerializableExtra("pedimento");
        txtDetalleEjecutivo = binding.txtDetalleEjecutivo;
        txtDetalleNoPedimento = binding.txtDetalleNoPedimento;
        txtDetalleFechaCreacion= binding.txtDetalleFechaCreacion;
        txtDetalleFechaArribo = binding.txtDetalleFechaArribo;
        txtDetalleSucursal = binding.txtDetalleSucursal;
        txtDetalleEstado = binding.txtDetalleEstado;
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtDetalleEjecutivo.setText(ped.getNomEjecutivo());
        txtDetalleNoPedimento.setText(ped.getNoPedimento());
        txtDetalleFechaCreacion.setText(ped.getFechaCreacion());
        txtDetalleFechaArribo.setText(ped.getFechaCreacion());
        txtDetalleSucursal.setText(ped.getSucursal());
        txtDetalleEstado.setText((ped.getEstado() == 0)?"EN PROGRESO":"FINALIZADO");


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_opciones,menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuEditar:
                Intent intent = new Intent(getContext(),AgregarPedimentoActivity.class);
                intent.putExtra("pedimento",ped);
                startActivity(intent);
                break;
            case R.id.menuEliminar:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.out.println("Eliminando");
                        EliminarPedimento();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.out.println("Cancelar");
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.setMessage("ELIMINAR REGISTRO?");
                dialog.show();

                break;
        }
        return true;
    }


    public void EliminarPedimento(){

        VolleySingleton.getInstance(getContext())
                .addToRequestQueue(
                        new JsonObjectRequest(
                                Request.Method.DELETE,
                                Constantes.eliminarPedimentos+ped.getIdPedimento(),
                                null,
                                response -> {

                                    try {
                                        JSONObject obj = response;
                                        if(obj.getInt("error") == 1){
                                            Snackbar.make(this.getView(), obj.getString("descripcion"), Snackbar.LENGTH_LONG).show();
                                        }else{
                                            Snackbar.make(this.getView(), obj.getString("descripcion"), Snackbar.LENGTH_LONG).show();
                                            Intent intent = new Intent(getContext(),PedimentosActivity.class);
                                            startActivity(intent);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                },
                                error -> {

                                    System.out.println(error.toString());
                                    Snackbar.make(this.getView(),error.toString(),Snackbar.LENGTH_LONG).show();
                                }
                        ){


                            @Override
                            public String getBodyContentType() {
                                return "application/json";
                            }
                        }
                );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}