package com.example.paises_api.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.paises_api.helper.SQLiteDataHelper;

import java.util.ArrayList;

import com.example.paises_api.model.Pais;

public class PaisDAO implements GenericDAO<Pais>{

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase bd;
    private String nomeTabela = "PAIS";
    private String[]colunas = {"CODIGO", "DESCRICAO"};
    private Context context;
    private static PaisDAO instancia;

    public static PaisDAO getInstancia(Context context){
        if(instancia == null){
            return instancia = new PaisDAO(context);
        }else{
            return instancia;
        }
    }

    private PaisDAO(Context context) {
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "UNIPAR_BD",
                null, 1);
        bd = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Pais obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getCodigo());
            valores.put(colunas[1], obj.getDescricao());

            return bd.insert(nomeTabela, null, valores);

        }catch (SQLException ex){
            Log.e("ERRO", "PaisDAO.insert(): "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Pais obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getDescricao());

            String[]identificador = {String.valueOf(obj.getCodigo())};
            return bd.update(nomeTabela, valores,
                    colunas[0]+" = ?", identificador);


        }catch (SQLException ex){
            Log.e("ERRO", "PaisDAO.update(): "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Pais obj) {
        try{

            String[]identificador = {String.valueOf(obj.getCodigo())};
            return bd.delete(nomeTabela, colunas[0]+" = ?",
                    identificador);

        }catch (SQLException ex){
            Log.e("ERRO", "PaisDAO.delete(): "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Pais> getAll() {
        ArrayList<Pais> lista = new ArrayList<>();
        try{
            Cursor cursor = bd.query(nomeTabela, colunas,
                    null, null, null,
                    null, colunas[0]);

            if(cursor.moveToFirst()){
                do{
                    Pais pais = new Pais();
                    pais.setCodigo(cursor.getInt(0));
                    pais.setDescricao(cursor.getString(1));

                    lista.add(pais);

                }while (cursor.moveToNext());
            }
        }catch (SQLException ex){
            Log.e("ERRO", "PaisDAO.getAll(): "+ex.getMessage());
        }
        return lista;
    }

    @Override
    public Pais getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = bd.query(nomeTabela, colunas,
                    colunas[0]+" = "+id, null,
                    null, null, null);

            if(cursor.moveToFirst()){
                Pais pais = new Pais();
                pais.setCodigo(cursor.getInt(0));
                pais.setDescricao(cursor.getString(1));

                return pais;
            }

        }catch (SQLException ex){
            Log.e("ERRO", "PaisDAO.getById(): "+ex.getMessage());
        }

        return null;
    }

}
