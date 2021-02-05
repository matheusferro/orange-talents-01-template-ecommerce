package br.com.zup.mercadoLivre.pergunta;

import br.com.zup.mercadoLivre.produto.Produto;
import br.com.zup.mercadoLivre.usuario.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static org.springframework.util.Assert.notNull;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String titulo;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Pergunta(){}

    public Pergunta(String titulo, Usuario usuario, Produto produto) {
        notNull(usuario, "Usuario invalido para cadastro de pergunta.");
        notNull(usuario, "Produto invalido para cadastro de pergunta.");
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getEmailDono() {
        return produto.getUsuario().getEmail();
    }
}
