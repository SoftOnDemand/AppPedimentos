package com.softon.apppedimentos.Controladores.Pedimentos;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.softon.apppedimentos.R;
import com.softon.apppedimentos.modelos.Pedimento;

public class PedimentosViewHolder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView noPedimento;
    TextView nomEjecutivo;
    Button  btnEditarPedimento;

    public PedimentosViewHolder(@NonNull View itemView) {
        super(itemView);
        nomEjecutivo = itemView.findViewById(R.id.txtNomEjecutivo);
        noPedimento = itemView.findViewById(R.id.txtNoPedimento);
        cv = itemView.findViewById(R.id.cvPedimentos);
        btnEditarPedimento = itemView.findViewById(R.id.btnEditarPedimento);
    }

    public void bind(final Pedimento pedimento){

    }

}
