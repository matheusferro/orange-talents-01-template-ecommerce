package br.com.zup.mercadoLivre.avaliacao;

import antlr.Token;
import br.com.zup.mercadoLivre.produto.Produto;
import br.com.zup.mercadoLivre.security.TokenService;
import br.com.zup.mercadoLivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class AvaliacaoProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/produto/{id}/avaliacao")
    @Transactional
    public ResponseEntity<?> avaliar(@RequestBody @Valid ProdutoAvaliacaoRequest avaliacaoRequest,
                                     @PathVariable("id") Long idProduto,
                                     @AuthenticationPrincipal Usuario usuarioLogado){

        Produto produto = entityManager.find(Produto.class, idProduto);

        //Realizar testes automatizados (é uma condicional/branch)
        if(produto == null || produto.produtoPertenceUsuario(usuarioLogado)){
            return ResponseEntity.badRequest().body("Escolha um produto valido e que não te pertence.");
        }

        Avaliacao avaliacao = avaliacaoRequest.toModel(produto, usuarioLogado);

        entityManager.persist(avaliacao);
        return ResponseEntity.ok().body(new AvaliacaoResponse(avaliacao));
    }
}
