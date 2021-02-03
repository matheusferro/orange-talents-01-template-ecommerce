package br.com.zup.mercadoLivre.anotacao;

import br.com.zup.mercadoLivre.usuario.Usuario;
import br.com.zup.mercadoLivre.usuario.UsuarioRepository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;

/**
 * Validador de email unico.
 *
 * @author Matheus Ferro
 * @since 1.0
 */
public class EmailUnicoValidator implements ConstraintValidator<EmailUnico, String> {

    private UsuarioRepository usuarioRepository;

    public EmailUnicoValidator(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void initialize(EmailUnico constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return false;
        }

        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(value.toString());
        return optUsuario.isEmpty();
    }
}
