package br.com.zup.mercadoLivre.usuario;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioCadastroRequest {
    @Email
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    @Length(min = 6)
    private String senha;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel() {
       return new Usuario(email, senha);
    }
}