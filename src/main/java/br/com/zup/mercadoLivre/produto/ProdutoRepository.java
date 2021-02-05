package br.com.zup.mercadoLivre.produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto findByIdAndUsuarioId(Long idProduto, Long idUsuario);
}
