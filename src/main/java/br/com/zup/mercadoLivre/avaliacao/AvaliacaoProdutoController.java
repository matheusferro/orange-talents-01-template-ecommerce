package br.com.zup.mercadoLivre.avaliacao;

import antlr.Token;
import br.com.zup.mercadoLivre.produto.Produto;
import br.com.zup.mercadoLivre.security.TokenService;
import br.com.zup.mercadoLivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
                                     @RequestHeader("Authorization") String token){

        Long idUsuario = tokenService.getIdUsuarioLogado(token);
        Usuario usuario = entityManager.find(Usuario.class, idUsuario);
        Produto produto = entityManager.find(Produto.class, idProduto);

        //Realizar testes automatizados (é uma condicional/branch)
        if(produto == null || produto.getIdUsuario() == idUsuario){
            return ResponseEntity.badRequest().body("Escolha um produto valido e que não te pertence.");
        }

        Avaliacao avaliacao = avaliacaoRequest.toModel(produto, usuario);

        entityManager.persist(avaliacao);
        return ResponseEntity.ok().body(avaliacaoRequest.toString());
    }
}
