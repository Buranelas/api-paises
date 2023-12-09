package com.example.paises_api.controller;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.paises_api.dao.PaisDAO;
import com.example.paises_api.dto.PaisesDTO;
import com.example.paises_api.model.Pais;
import com.example.paises_api.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaisController {

    private Context context;

    public PaisController(Context context) {
        this.context = context;
    }

    public void getPais(Context context){

        ArrayList<Pais> listaPaisInsercao = new ArrayList<>();
        
        try{

            Call<ArrayList<PaisesDTO>> call = new RetrofitConfig()
                    .paisService().getPais();

            call.enqueue(new Callback<ArrayList<PaisesDTO>>() {
                @Override
                public void onResponse(Call<ArrayList<PaisesDTO>> call, Response<ArrayList<PaisesDTO>> response) {
                    ArrayList<PaisesDTO> dto = response.body();
                    for (PaisesDTO pais : dto) {

                        Pais enityPais = new Pais();
                        enityPais.setCodigo(pais.getCodigo());
                        enityPais.setDescricao(pais.getDescricao());

                        PaisDAO.getInstancia(context).insert(enityPais);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<PaisesDTO>> call, Throwable t) {
                    //tvResponse.setText(t.getMessage());
                    String erro = t.getMessage();
                }
            });

            /*
            call.enqueue(new Callback<ArrayList<PaisesDTO>>() {
                @Override
                public void onResponse(Call<ArrayList<PaisesDTO>> call, Response<ArrayList<PaisesDTO>> response) {
                    ArrayList<PaisesDTO> dto = response.body();
                    for (PaisesDTO pais : dto) {

                    }
                }

                @Override
                public void onFailure(Call<PaisesDTO> call, Throwable t) {
                    tvResponse.setText(t.getMessage());
                }
            });*/

        }catch (Exception ex){
            String erro = ex.getMessage();
        }



    }

    private static void salvarPais(int codigo, String descricao) {
    }

    public String salvarPais(String codigo, String descricao){
        try{
            if(codigo.equals("") || codigo.isEmpty()){
                return "Informe o Codigo do Pais!";
            }
            if(descricao.equals("") || descricao.isEmpty()){
                return "Informe o Nome do Pais!";
            }

            Pais pais = PaisDAO.getInstancia(context)
                    .getById(Integer.parseInt(codigo));

            if(pais != null){
                return "O Código ("+codigo+") já está cadastrado!";
            }else{
                pais = new Pais();
                pais.setCodigo(Integer.parseInt(codigo));
                pais.setDescricao(descricao);

                PaisDAO.getInstancia(context).insert(pais);
            }
        }catch (Exception ex){
            return "Erro ao gravar Pais.";
        }
        return null;
    }

    public ArrayList<Pais> retornaPaises(){
        return PaisDAO.getInstancia(context).getAll();
    }
}
