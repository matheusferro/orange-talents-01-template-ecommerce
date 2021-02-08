package br.com.zup.mercadoLivre.produto.imagem;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImagemLinkResponse {

    @JsonProperty
    private String link;

    public ImagemLinkResponse(Imagem imagem){
        link = imagem.getLink();
    }
}
