package com.softon.apppedimentos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.material.snackbar.Snackbar;
import com.softon.apppedimentos.Controladores.Pedimentos.RVPedAdapter;
import com.softon.apppedimentos.conexionWS.Constantes;
import com.softon.apppedimentos.conexionWS.VolleySingleton;
import com.softon.apppedimentos.databinding.FragmentFirstBinding;
import com.softon.apppedimentos.modelos.Pedimento;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    RecyclerView rv;
    ProgressDialog pdActualizar;
    private List<Pedimento> pedimentos = new ArrayList<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        rv = binding.rvPedimentos;

        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());

        rv.setLayoutManager(llm);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),llm.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);

        pdActualizar = new ProgressDialog(getContext());

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        obtenerPedimentosList();


    }

    public void obtenerPedimentosList(){
        showProgress(true);
        VolleySingleton.getInstance(getContext())
                .addToRequestQueue(
                        new JsonArrayRequest(
                                Request.Method.GET,
                                Constantes.getPedimentos,
                                null,
                                response -> {
                                    pedimentos.clear();
                                   showProgress(false);
                                    try {
                                        for (int i = 0; i< response.length(); i++) {

                                                JSONObject obj = response.getJSONObject(i);
                                                System.out.println(obj.toString());
                                                pedimentos.add(new Pedimento(
                                                        obj.getString("noPedimento"),
                                                        obj.getInt("idPedimento"),
                                                        obj.getInt("idEjecutivo"),
                                                        obj.getString("ejecutivo"),
                                                        obj.getString("fechaArribo"),
                                                        obj.getInt("estado"),
                                                        obj.getString("fechaCreacion"),
                                                        obj.getInt("idSucursal"),
                                                        obj.getString("sucursal")
                                                ));
                                                 rv.setAdapter(new RVPedAdapter(pedimentos, new RVPedAdapter.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(Pedimento item) {
//                                                        System.out.println(item.getNoPedimento());
                                                    }
                                                },getContext(),1,this));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                },
                                error -> {
                                    showProgress(false);
                                    System.out.println(error.toString());
                                }
                        ){


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
                pdActualizar.setTitle("Cargando Datos");
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}