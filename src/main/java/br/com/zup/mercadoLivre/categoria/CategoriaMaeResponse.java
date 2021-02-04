package br.com.zup.mercadoLivre.categoria;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoriaMaeResponse {

    @JsonProperty
    private String nome;

    public CategoriaMaeResponse(Categoria categoriaMae) {
        if(categoriaMae != null) {
            this.nome = categoriaMae.getNome();
        }
    }
}
