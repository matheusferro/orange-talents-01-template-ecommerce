package br.com.zup.mercadoLivre.usuario;

import br.com.zup.mercadoLivre.security.EncriptaSenha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class UsuarioController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EncriptaSenha encriptar;

    @PostMapping(path = "/usuario")
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioCadastroRequest request){
        Usuario usuario = request.toModel();
        em.persist(usuario);
        return ResponseEntity.ok().build();
    }
}
