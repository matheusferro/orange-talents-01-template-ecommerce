package br.com.zup.mercadoLivre.anotacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Anotacao para utilizar na validacao de unicidade de email.
 * @author Matheus Ferro
 * @since 1.0
 */
@Documented
@Constraint(validatedBy = {EmailUnicoValidator.class })
@Target({FIELD})
@Retention(RUNTIME)
public @interface EmailUnico {

    String message() default "Email deve ser único";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
