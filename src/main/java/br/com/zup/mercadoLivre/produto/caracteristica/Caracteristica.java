package br.com.zup.mercadoLivre.produto.caracteristica;

import br.com.zup.mercadoLivre.produto.Produto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public Caracteristica(){}

    public Caracteristica(@NotNull String nome, @NotNull String descricao, Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
