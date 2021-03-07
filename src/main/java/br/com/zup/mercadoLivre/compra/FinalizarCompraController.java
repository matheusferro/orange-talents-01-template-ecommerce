package br.com.zup.mercadoLivre.compra;

import br.com.zup.mercadoLivre.email.EmailService;
import br.com.zup.mercadoLivre.produto.Produto;
import br.com.zup.mercadoLivre.produto.ProdutoRepository;
import br.com.zup.mercadoLivre.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class FinalizarCompraController {

    private ProdutoRepository produtoRepository;

    private CompraRepository compraRepository;

    private EmailService emails;

    public FinalizarCompraController(ProdutoRepository produtoRepository, CompraRepository compraRepository, EmailService emails) {
        this.produtoRepository = produtoRepository;
        this.compraRepository = compraRepository;
        this.emails = emails;
    }

    @Transactional
    @PostMapping(path = "/compra")
    public Object finalizarCompra(@RequestBody @Valid FinalizarCompraRequest request,
                                             @AuthenticationPrincipal Usuario usuario,
                                             UriComponentsBuilder uriComponentsBuilder) throws BindException {
        Optional<Produto> produto = produtoRepository.findById(request.getIdProduto());
        if(produto.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        if(produto.get().abaterQuantidade(request.getQuantidade())){

            Compra compra = request.toModel(produto.get(), usuario);
            compraRepository.save(compra);

            emails.compraRealizada(compra);
            return request.getGetway().getUrl(compra, uriComponentsBuilder);
        }

        BindException problemaEstoque = new BindException(request, "finalizarCompraRequest");
        problemaEstoque.reject(null, "Ocorreu um erro ao tentar realizar a compra.");
        throw problemaEstoque;
    }
}
