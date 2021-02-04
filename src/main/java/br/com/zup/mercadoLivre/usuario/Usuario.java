package br.com.zup.mercadoLivre.usuario;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.regex.Pattern;

@Entity
public class Usuario implements UserDetails {
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
        /**
         * BCRYPT_PATTERN = encontrado dentro da classe BCryptPasswordEncoder
         * onde é utilizado para a verificação de parâmetros do tipo BCrypt.
         * @see org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
         */
        final Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");
        final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        /**
         * Adicionado algumas validações aplicando conceitos de programação
         * defensiva
         */
        Assert.notNull(email, "Não é possível cadastrar um email nullo.");
        Assert.isTrue(EMAIL_PATTERN.matcher(email).matches(), "Email inválido.");

        Assert.notNull(senha, "Não é possível cadastrar uma senha nullo.");
        Assert.hasLength(senha, "Não é possível cadastrar uma senha vazia.");
        Assert.isTrue(senha.length() >= 6, "A senha deve conter mais que 5 caracteres");
        Assert.isTrue(!BCRYPT_PATTERN.matcher(senha).matches(), "A senha deve ser limpa.");

        this.email = email;
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id) && email.equals(usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
