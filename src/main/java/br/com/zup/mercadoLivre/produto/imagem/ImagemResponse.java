package br.com.zup.mercadoLivre.produto.imagem;

import br.com.zup.mercadoLivre.produto.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ImagemResponse {
    @JsonProperty
    private String produto;
    @JsonProperty
    private Set<ImagemLinkResponse> imagens = new HashSet<>();


    public ImagemResponse(Produto produtoDoUsuario) {
        this.produto = produtoDoUsuario.getNome();
        this.imagens = produtoDoUsuario.getImagemList().stream().map(img -> new ImagemLinkResponse(img)).collect(Collectors.toSet());
    }

}
