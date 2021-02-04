package br.com.zup.mercadoLivre.produto;

import br.com.zup.mercadoLivre.anotacao.ExistsValue;
import br.com.zup.mercadoLivre.categoria.Categoria;
import br.com.zup.mercadoLivre.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


public class ProdutoCadastroRequest {

    @NotNull
    @NotBlank
    @JsonProperty("nome")
    private String nome;

    @NotNull
    @Positive
    @JsonProperty("valor")
    private BigDecimal valor;

    @NotNull
    @Positive
    @JsonProperty("quantidade")
    private Integer quantidade;

    @NotNull
    @Size(min=3)
    @JsonProperty("caracteristicas")
    private Set<CaracteristicaRequest> caracteristicas = new HashSet<>();

    @NotBlank
    @Length(max=1000)
    @JsonProperty("descricao")
    private String descricao;

    @NotNull
    @ExistsValue(domainClass = Categoria.class, fieldName = "id")
    @JsonProperty("idCategoria")
    private Long idCategoria;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ProdutoCadastroRequest(@NotNull @NotBlank String nome,
                                  @NotNull @Positive BigDecimal valor,
                                  @NotNull @Positive Integer quantidade,
                                  @NotBlank @Length(max = 1000) String descricao,
                                  @NotNull Long idCategoria,
                                  @NotNull @Valid Set<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    public Set<CaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public Produto toModel(EntityManager entityManager, Usuario usuario) {
        Categoria categoria = entityManager.find(Categoria.class, idCategoria);
        return new Produto(nome, valor, quantidade, descricao, categoria, usuario, this.caracteristicas);
    }
}
