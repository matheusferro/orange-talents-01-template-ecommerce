package br.com.zup.mercadoLivre.categoria;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CategoriaControllerTests {

    @Autowired
    private ObjectMapper jsonMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Cadastro de categoria sem categoria mãe.")
    void cadastroCategoriaSemMae()throws Exception{

        CategoriaCadastroRequest cadastroRequest = new CategoriaCadastroRequest("Informática", null);

        CategoriaRepository repository = Mockito.mock(CategoriaRepository.class);
        Categoria novaCategoria = new Categoria("Informática", null);
        Mockito.when(repository.save(novaCategoria)).thenAnswer(i -> i.getArguments()[0]);;

        mockMvc.perform(MockMvcRequestBuilders.post("/categoria")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(cadastroRequest))
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Informática"))
                .andExpect(jsonPath("$.idCategoriaMae").isEmpty());
    }

    @Test
    @DisplayName("Cadastro de categoria com categoria mãe NÃO existente.")
    void cadastroCategoriaComMaeInexistente()throws Exception{

        CategoriaCadastroRequest cadastroRequest = new CategoriaCadastroRequest("Informática", 300L);

        CategoriaRepository repository = Mockito.mock(CategoriaRepository.class);
        Categoria novaCategoria = new Categoria("Informática", null);
        Mockito.when(repository.save(novaCategoria)).thenAnswer(i -> i.getArguments()[0]);;

        mockMvc.perform(MockMvcRequestBuilders.post("/categoria")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(cadastroRequest))
        ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[?(@.campo == 'idCategoriaMae')].erro").value("Categoria mãe não existe."));
    }

    @Test
    @DisplayName("Cadastro de categoria com categoria mãe existente.")
    void cadastroCategoriaComMae()throws Exception{

        CategoriaCadastroRequest cadastroRequest = new CategoriaCadastroRequest("Informática", null);

        CategoriaRepository repository = Mockito.mock(CategoriaRepository.class);

        Categoria novaCategoria = new Categoria("Informática", null);
        Mockito.when(repository.save(novaCategoria)).thenAnswer(i -> i.getArguments()[0]);;

        mockMvc.perform(MockMvcRequestBuilders.post("/categoria")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(cadastroRequest))
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Informática"))
                .andExpect(jsonPath("$.idCategoriaMae").isEmpty());

        cadastroRequest = new CategoriaCadastroRequest("Celulares", 1L);

        Categoria novaCategoriaComMae = cadastroRequest.toModel();
        Mockito.when(repository.save(novaCategoriaComMae)).thenAnswer(i -> i.getArguments()[0]);;

        mockMvc.perform(MockMvcRequestBuilders.post("/categoria")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(cadastroRequest))
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Celulares"))
                .andExpect(jsonPath("$.idCategoriaMae").value(1));
    }

}
