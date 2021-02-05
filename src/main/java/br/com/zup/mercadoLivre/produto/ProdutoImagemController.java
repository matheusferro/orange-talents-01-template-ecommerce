package br.com.zup.mercadoLivre.produto;

import br.com.zup.mercadoLivre.security.TokenService;
import br.com.zup.mercadoLivre.upload.Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Set;

@RestController
public class ProdutoImagemController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private Uploader uploader;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/produto/{id}/imagem")
    @Transactional
    public ResponseEntity<?> cadastroImagem(@Valid ImagemCadastroRequest requestImg,
                                            @PathVariable("id") Long idProduto,
                                            @RequestHeader("Authorization") String token) {
        Long idLogado = tokenService.getIdUsuarioLogado(token);

        Produto produtoDoUsuario = produtoRepository.findByIdAndUsuarioId(idProduto,idLogado);
        if(produtoDoUsuario == null){
            return ResponseEntity.badRequest().build();
        }

        Set<String> links = uploader.upload(requestImg.getImagens());
        produtoDoUsuario.adicionarImagens(links);
        produtoRepository.save(produtoDoUsuario);

        return ResponseEntity.ok().body(new ImagemResponse(produtoDoUsuario, links));
    }
    /**
     * MultipartFile img = requestImg.getImagens().iterator().next();
     * return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(IOUtils.toByteArray(img.getInputStream()));
     *
     * RETORNAR IMAGEM.
     */
}
