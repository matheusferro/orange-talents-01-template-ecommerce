package br.com.zup.mercadoLivre.produto.imagem;

import br.com.zup.mercadoLivre.produto.Produto;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @URL
    @NotBlank
    @NotNull
    private String link;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public Imagem(){}

    public Imagem(@NotNull String link, Produto produto) {
        this.link = link;
        this.produto = produto;
    }

    public String getLink() {
        return link;
    }
}
