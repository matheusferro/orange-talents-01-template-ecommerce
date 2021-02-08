package br.com.zup.mercadoLivre.produto;

import br.com.zup.mercadoLivre.categoria.Categoria;
import br.com.zup.mercadoLivre.produto.caracteristica.CaracteristicaRequest;
import br.com.zup.mercadoLivre.usuario.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestProdutoUnidade {

    @DisplayName("Teste com três caracteristicas.")
    @ParameterizedTest
    @MethodSource("geradorTeste1")
    void teste(Set<CaracteristicaRequest> caracteristicas) throws Exception{
        Categoria categoria = new Categoria("categoria", null);
        Usuario usuario = new Usuario("email@teste.com","1234565");

        new Produto("Produto", BigDecimal.TEN, 10, "Desc", categoria, usuario, caracteristicas);
    }

    @DisplayName("Teste com menos de três caracteristicas.")
    @ParameterizedTest
    @MethodSource("geradorTeste2")
    void testeExcecao(Set<CaracteristicaRequest> caracteristicas) throws Exception{
        Categoria categoria = new Categoria("categoria", null);
        Usuario usuario = new Usuario("email@teste.com","1234565");

        assertThrows(IllegalArgumentException.class,() ->
                new Produto("Produto", BigDecimal.TEN, 10, "Desc", categoria, usuario, caracteristicas)
        );
    }

    static Stream<Arguments> geradorTeste1(){
        return Stream.of(
                Arguments.of(
                        Set.of(
                                new CaracteristicaRequest("nome1","desc1"),
                                new CaracteristicaRequest("nome2","desc2"),
                                new CaracteristicaRequest("nome3","desc3")
                        )
                ),
                Arguments.of(
                        Set.of(
                                new CaracteristicaRequest("nome4","desc1"),
                                new CaracteristicaRequest("nome5","desc2"),
                                new CaracteristicaRequest("nome6","desc3")
                        )
                )
        );
    }

    static Stream<Arguments> geradorTeste2(){
        return Stream.of(
                Arguments.of(
                        Set.of(
                                new CaracteristicaRequest("nome","desc"),
                                new CaracteristicaRequest("nome2","desc")
                        )
                ),
                Arguments.of(
                        Set.of(
                                new CaracteristicaRequest("nome1","desc1")
                        )
                )
        );
    }
}
