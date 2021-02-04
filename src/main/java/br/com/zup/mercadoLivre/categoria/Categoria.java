package br.com.zup.mercadoLivre.categoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria idCategoriaMae;

    @Deprecated
    public Categoria(){}

    public Categoria(@NotBlank String nome, Categoria idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getIdCategoriaMae() {
        return idCategoriaMae;
    }
}