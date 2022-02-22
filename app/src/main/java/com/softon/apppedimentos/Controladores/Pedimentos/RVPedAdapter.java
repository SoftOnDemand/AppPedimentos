package com.softon.apppedimentos.Controladores.Pedimentos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.softon.apppedimentos.R;
import com.softon.apppedimentos.modelos.Pedimento;

import java.util.List;

public class RVPedAdapter extends RecyclerView.Adapter<PedimentosViewHolder> {
    List<Pedimento> pedimentoList;
    OnItemClickListener listener;
    Context context;
    int idPedimento;
    Fragment fragment;

    public interface OnItemClickListener{
        void onItemClick(Pedimento item);
    }


    public RVPedAdapter(List<Pedimento> pedimentoList, OnItemClickListener listener, Context context, int idPedimento, Fragment fragment){
        this.pedimentoList = pedimentoList;
        this.listener = listener;
        this.context = context;
        this.idPedimento = idPedimento;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public PedimentosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pedimentos,parent,false);

        return new PedimentosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedimentosViewHolder holder, int position) {
    holder.btnEditarPedimento.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
             NavHostFragment.findNavController(fragment)
            .navigate(R.id.action_FirstFragment_to_SecondFragment);
        }
    });


    }

    @Override
    public int getItemCount() {
        return pedimentoList.size();
    }
}
