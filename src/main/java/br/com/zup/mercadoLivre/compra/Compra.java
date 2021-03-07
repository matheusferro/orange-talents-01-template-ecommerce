package br.com.zup.mercadoLivre.compra;

import br.com.zup.mercadoLivre.produto.Produto;
import br.com.zup.mercadoLivre.usuario.Usuario;
import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

import static io.jsonwebtoken.lang.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

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

    @Min(1)
    @NotNull
    private Integer quantidade;

    @Enumerated
    @NotNull
    private GetwayPagamento getwayPagamento;

    @Deprecated
    Compra(){}

    public Compra(@NotNull Produto produto,
                  @NotNull Usuario usuario,
                  @NotNull @Min(1) Integer quantidade,
                  GetwayPagamento getwayPagamento) {
        notNull(produto, "Necessário um produto para cadastrar compra.");
        notNull(usuario, "Necessário relacionar compra om um usuario.");
        isTrue(quantidade > 0, "Quantidade comprada precisa ser maior que 0");

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
