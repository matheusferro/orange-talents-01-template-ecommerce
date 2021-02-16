package br.com.zup.mercadoLivre.avaliacao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvaliacaoResponse {

    @JsonProperty
    private Integer nota;

    @JsonProperty
    private String titulo;

    @JsonProperty
    private String descricao;

    @JsonProperty
    private String nomeProduto;

    @JsonProperty
    private String usuario;

    public AvaliacaoResponse(Avaliacao avaliacao){
        this.nota = avaliacao.getNota();
        this.titulo = avaliacao.getTitulo();
        this.descricao = avaliacao.getDescricao();
        this.nomeProduto = avaliacao.getProduto().getNome();
        this.usuario = avaliacao.getUsuario().getEmail();
    }

}
