package br.com.zup.mercadoLivre.usuario;

import br.com.zup.mercadoLivre.anotacao.UniqueValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioCadastroRequest {
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "email")
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    @Length(min = 6)
    private String senha;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UsuarioCadastroRequest(@Email @NotBlank @JsonProperty("email") String email,
                                  @NotBlank @Length(min = 6) @JsonProperty("senha") String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario toModel() {
       return new Usuario(email, senha);
    }
}