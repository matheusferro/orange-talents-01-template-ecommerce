package br.com.zup.mercadoLivre.produto;

import java.util.HashSet;
import java.util.Set;

public class ImagemResponse {

    private String produto;
    private Set<String> imagens = new HashSet<>();


    public ImagemResponse(Produto produtoDoUsuario, Set<String> links) {
        this.produto = produtoDoUsuario.getNome();
        this.imagens = links;
    }

    public String getProduto() {
        return produto;
    }

    public Set<String> getImagens() {
        return imagens;
    }
}
