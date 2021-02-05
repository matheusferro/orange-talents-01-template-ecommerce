package br.com.zup.mercadoLivre.pergunta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PerguntaListaResponse {

    @JsonProperty
    private String titulo;

    public PerguntaListaResponse(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
    }
}
