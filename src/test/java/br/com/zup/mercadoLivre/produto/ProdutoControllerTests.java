package br.com.zup.mercadoLivre.produto;

import br.com.zup.mercadoLivre.categoria.Categoria;
import br.com.zup.mercadoLivre.categoria.CategoriaCadastroRequest;
import br.com.zup.mercadoLivre.categoria.CategoriaRepository;
import br.com.zup.mercadoLivre.usuario.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class ProdutoControllerTests {
    @Autowired
    private ObjectMapper jsonMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Cadastro de produto.")
    void cadastroProduto()throws Exception{
        Categoria categoria = new Categoria("categoria", null);
        
        Usuario usuario = new Usuario("email@teste.com","1234565");

        Set<CaracteristicaRequest> caracteristicas = Set.of(
                new CaracteristicaRequest("nome1","desc1"),
                new CaracteristicaRequest("nome2","desc2"),
                new CaracteristicaRequest("nome3","desc3")
        );
        ProdutoCadastroRequest cadastroRequest = new ProdutoCadastroRequest("nome", BigDecimal.TEN, 10, "Desc", 1L, caracteristicas);

        mockMvc.perform(MockMvcRequestBuilders.post("/produto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(cadastroRequest))
        ).andExpect(status().isOk());
    }

}
