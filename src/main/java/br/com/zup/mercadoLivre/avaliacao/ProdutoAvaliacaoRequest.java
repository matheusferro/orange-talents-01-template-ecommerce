package br.com.zup.mercadoLivre.avaliacao;

import br.com.zup.mercadoLivre.anotacao.ExistsValue;
import br.com.zup.mercadoLivre.avaliacao.Avaliacao;
import br.com.zup.mercadoLivre.produto.Produto;
import br.com.zup.mercadoLivre.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static org.springframework.util.Assert.notNull;

public class ProdutoAvaliacaoRequest {

    @NotNull
    @Min(1)
    @Max(5)
    @JsonProperty
    private int nota;

    @NotBlank
    @JsonProperty
    private String titulo;

    @Length(max = 500)
    @NotBlank
    @JsonProperty
    private String descricao;

    @Override
    public String toString() {
        return "ProdutoAvaliacaoRequest{" +
                "nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public Avaliacao toModel(Produto produto, Usuario usuario) {
        notNull(usuario, "Não foi possível cadastrar avaliação com esse usuário.");
        return new Avaliacao(this.nota, this.titulo, this.descricao, produto, usuario);
    }
}
