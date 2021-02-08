package br.com.zup.mercadoLivre.avaliacao;

import br.com.zup.mercadoLivre.produto.Produto;
import br.com.zup.mercadoLivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Max(5)
    @NotNull
    @NumberFormat
    private Integer nota;

    @NotBlank
    @NotNull
    private String titulo;

    @Length(max = 500)
    @NotBlank
    @NotNull
    private String descricao;

    @ManyToOne
    private Produto produto;

    @OneToOne
    private Usuario usuario;

    @Deprecated
    public Avaliacao(){}

    public Avaliacao(@Min(1) @Max(5) @NotNull Integer nota,
                     @NotBlank @NotNull String titulo,
                     @Length(max = 500) @NotBlank @NotNull String descricao,
                     Produto produto,
                     Usuario usuario) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuario = usuario;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
