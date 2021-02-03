package br.com.zup.mercadoLivre.usuario;

import br.com.zup.mercadoLivre.security.EncriptaSenha;
import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(unique = true, nullable = false)
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    @Length(min = 6)
    private String senha;

    @NotNull
    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @Deprecated
    public Usuario(){}

    public Usuario(@Email @NotBlank String email, @NotBlank @Length(min = 6) String senha) {
        this.email = email;
        this.senha = EncriptaSenha.encripitar(senha);
    }
}
