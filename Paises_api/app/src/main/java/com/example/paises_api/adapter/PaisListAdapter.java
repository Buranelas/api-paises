package com.example.paises_api.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paises_api.R;
import com.example.paises_api.model.Pais;

import java.util.ArrayList;

public class PaisListAdapter extends
        RecyclerView.Adapter<PaisListAdapter.PaisViewHolder>{

    private ArrayList<Pais> listaPaises;
    private Context context;

    public PaisListAdapter(ArrayList<Pais> listaPaises, Context context){
        this.listaPaises = listaPaises;
        this.context = context;

    }


    @NonNull
    @Override
    public PaisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listView = inflater.inflate(R.layout.item_list_paises, parent, false);

        return new PaisViewHolder(listView);
    }

    @Override
    public void onBindViewHolder(@NonNull PaisViewHolder holder, int position) {
        Pais paisSelecionado = listaPaises.get(position);
        holder.tvCodigo.setText(String.valueOf(paisSelecionado.getCodigo()));
        holder.tvDescricao.setText(paisSelecionado.getDescricao());
    }

    @Override
    public int getItemCount() {
        return listaPaises.size();
    }

    public class PaisViewHolder extends RecyclerView.ViewHolder{

        public TextView tvCodigo;
        public TextView tvDescricao;

        public PaisViewHolder(@NonNull View itemview){
            super(itemview);

            this.tvCodigo = itemview.findViewById(R.id.tvCodigo);
            this.tvDescricao = itemview.findViewById(R.id.tvDescricao);
        }
    }

}
