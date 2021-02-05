package br.com.zup.mercadoLivre.produto;

import br.com.zup.mercadoLivre.security.TokenService;
import br.com.zup.mercadoLivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ProdutoController {

    @Autowired
    private TokenService tokenService;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/produto")
    @Transactional
    public ResponseEntity<ProdutoCriadoResponse> cadastro(@RequestBody @Valid ProdutoCadastroRequest produtoRequest,
                                                          @AuthenticationPrincipal Usuario usuarioLogado){
        /**
         *  String emailDoUsuarioLogado = SecurityContextHolder.getContext().getAuthentication().getName();
         * Usuario simulado:
         * Usuario usuario = usuarioRepository.findByEmail("email@teste.com").get();
         */

        Produto produto = produtoRequest.toModel(entityManager, usuarioLogado);
        entityManager.persist(produto);

        return ResponseEntity.ok().body(new ProdutoCriadoResponse(produto, entityManager));
    }
}
