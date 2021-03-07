package br.com.zup.mercadoLivre.compra;

import br.com.zup.mercadoLivre.produto.Produto;
import br.com.zup.mercadoLivre.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class FinalizarCompraRequest {

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @Positive
    private Long idProduto;

    @NotNull
    private GetwayPagamento getway;

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public GetwayPagamento getGetway() {
        return getway;
    }

    @Override
    public String toString() {
        return "FinalizarCompraRequest{" +
                "quantidade=" + quantidade +
                ", idProduto=" + idProduto +
                '}';
    }

    public Compra toModel(Produto produto, Usuario usuario) {
        return new Compra(produto, usuario, this.quantidade, this.getway);
    }
}
