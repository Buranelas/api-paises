package com.example.paises_api.service;

import com.example.paises_api.dto.PaisesDTO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IPaisService {

    @GET("/api/paises")
    Call<ArrayList<PaisesDTO>> getPais();
}
