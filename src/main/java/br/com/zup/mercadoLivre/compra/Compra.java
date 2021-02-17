package br.com.zup.mercadoLivre.compra;

import br.com.zup.mercadoLivre.produto.Produto;
import br.com.zup.mercadoLivre.usuario.Usuario;
import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @NotNull
    @ManyToOne
    private Produto produto;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @Positive
    private int quantidade;

    @Enumerated
    @NotNull
    private GetwayPagamento getwayPagamento;

    @Deprecated
    Compra(){}

    public Compra(Produto produto, Usuario usuario, int quantidade, GetwayPagamento getwayPagamento) {
        this.produto = produto;
        this.usuario = usuario;
        this.quantidade = quantidade;
        this.getwayPagamento = getwayPagamento;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
