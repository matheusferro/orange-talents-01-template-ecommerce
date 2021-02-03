package br.com.zup.mercadoLivre.usuario;

import br.com.zup.mercadoLivre.anotacao.EmailUnicoValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteUnicidadeEmail {

    @DisplayName("Unicidade de Email")
    @ParameterizedTest
    @MethodSource("geradorUnicidadeEmailCadastro")
    public void unicidadeEmailCadastro(Optional<Usuario> optUsuario, boolean esperado){
        //Mockando o repository, para não precisar nos conectar com o banco
        UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);

        //Mockando o resultado do metodo que vai ser chamado pelo validador
        Mockito.when(usuarioRepository.findByEmail("email@teste.com")).thenReturn(optUsuario);

        //utilizando o validador.
        EmailUnicoValidator emailValidator = new EmailUnicoValidator(usuarioRepository);

        assertEquals(esperado, emailValidator.isValid("email@teste.com", null));
    }

    static Stream<Arguments> geradorUnicidadeEmailCadastro(){
        Optional<Usuario> usuario = Optional.of(new Usuario("email@teste.com", "123456"));
        return Stream.of(Arguments.of(usuario, false),
                Arguments.of(Optional.empty(), true));
    }
}
