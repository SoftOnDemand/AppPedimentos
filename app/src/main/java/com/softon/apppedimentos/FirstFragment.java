package com.softon.apppedimentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softon.apppedimentos.Controladores.Pedimentos.RVPedAdapter;
import com.softon.apppedimentos.databinding.FragmentFirstBinding;
import com.softon.apppedimentos.modelos.Pedimento;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    RecyclerView rv;

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



        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv.setAdapter(new RVPedAdapter(obtenerPedimentosList(), new RVPedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pedimento item) {
                System.out.println(item.getNoPedimento());
            }
        },getContext(),1,this));

    }

    public List<Pedimento> obtenerPedimentosList(){
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        pedimentos.add(new Pedimento("1234567",1,1,"Angel Cabrales"));
        return pedimentos;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}