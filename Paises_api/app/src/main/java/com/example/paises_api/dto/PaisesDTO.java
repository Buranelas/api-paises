package com.example.paises_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaisesDTO {
    @JsonProperty("Descrição")
    private String descricao;
    @JsonProperty("Código")
    private int codigo;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
