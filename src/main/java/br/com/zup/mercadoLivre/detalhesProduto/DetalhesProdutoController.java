package br.com.zup.mercadoLivre.detalhesProduto;

import br.com.zup.mercadoLivre.produto.Produto;
import br.com.zup.mercadoLivre.produto.ProdutoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DetalhesProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produto/{id}")
    public ResponseEntity<DetalhesProduto> detalhesProduto(@PathVariable("id") Long id){

        Optional<Produto> produto = produtoRepository.findById(id);
        DetalhesProduto detalhes = new DetalhesProduto(produto);
        return ResponseEntity.ok().body(detalhes);
    }
}
