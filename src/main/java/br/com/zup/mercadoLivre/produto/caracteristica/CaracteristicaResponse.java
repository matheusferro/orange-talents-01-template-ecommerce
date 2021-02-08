package br.com.zup.mercadoLivre.produto.caracteristica;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CaracteristicaResponse {

    @JsonProperty
    private String nome;

    @JsonProperty
    private String descricao;

    public CaracteristicaResponse(Caracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }
}
