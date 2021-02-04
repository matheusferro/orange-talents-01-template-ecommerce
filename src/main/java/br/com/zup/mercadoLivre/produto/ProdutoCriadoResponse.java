package br.com.zup.mercadoLivre.produto;

import br.com.zup.mercadoLivre.categoria.Categoria;
import br.com.zup.mercadoLivre.categoria.CategoriaResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoCriadoResponse {

    @JsonProperty
    private String nome;

    @JsonProperty
    private BigDecimal valor;

    @JsonProperty
    private Integer quantidade;

    @JsonProperty
    private Set<CaracteristicaResponse> caracteristicas = new HashSet<>();

    @JsonProperty
    private String descricao;

    @JsonProperty
    private CategoriaResponse categoria;


    public ProdutoCriadoResponse(Produto produto, EntityManager entityManager){
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();
        this.descricao = produto.getDescricao();

        Categoria categoria = entityManager.find(Categoria.class, produto.getCategoria().getId());
        this.categoria = new CategoriaResponse(categoria.getId(), categoria.getNome(), categoria.getIdCategoriaMae());

        Set<CaracteristicaResponse> setCaracteristicasResponse = produto.getCaracteristicas().stream()
                .map(carac -> new CaracteristicaResponse(carac))
                .collect(Collectors.toSet());
        this.caracteristicas.addAll(setCaracteristicasResponse);
    }

}
