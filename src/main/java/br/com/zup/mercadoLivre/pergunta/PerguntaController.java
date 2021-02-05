package br.com.zup.mercadoLivre.pergunta;

import br.com.zup.mercadoLivre.email.EmailService;
import br.com.zup.mercadoLivre.email.EnvioEmail;
import br.com.zup.mercadoLivre.produto.Produto;
import br.com.zup.mercadoLivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PerguntaController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private EmailService emails;

    @PostMapping("/produto/{id}/pergunta")
    @Transactional
    public ResponseEntity<?> cadastro(@RequestBody @Valid PerguntaCadastroRequest perguntaRequest,
                                      @PathVariable("id") Long idProduto,
                                      @AuthenticationPrincipal Usuario usuarioLogado){
        Produto produto = entityManager.find(Produto.class, idProduto);
        if(produto == null || produto.produtoPertenceUsuario(usuarioLogado)){
            return ResponseEntity.badRequest().body("Escolha um produto valido e que não te pertence.");
        }


        Pergunta pergunta = perguntaRequest.toModel(usuarioLogado, produto);
        entityManager.persist(pergunta);

        List<Pergunta> perguntas = perguntaRepository.findByProdutoId(idProduto);
        List<PerguntaListaResponse> listPergunta = perguntas.stream()
                .map(perguntaItem -> new PerguntaListaResponse(perguntaItem)).collect(Collectors.toList());

        emails.novaPergunta(pergunta);

        return ResponseEntity.ok().body(listPergunta);
    }

}
