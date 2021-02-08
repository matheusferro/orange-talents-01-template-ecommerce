package br.com.zup.mercadoLivre.detalhesProduto;

import br.com.zup.mercadoLivre.avaliacao.AvaliacaoResponse;
import br.com.zup.mercadoLivre.pergunta.PerguntaListaResponse;
import br.com.zup.mercadoLivre.produto.Produto;
import br.com.zup.mercadoLivre.produto.caracteristica.CaracteristicaResponse;
import br.com.zup.mercadoLivre.produto.imagem.ImagemLinkResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class DetalhesProduto {

    @JsonProperty
    private String nome;

    @JsonProperty
    private BigDecimal preco;

    @JsonProperty
    private String descricao;

    @JsonProperty
    private int totalDeAvaliacoes;

    @JsonProperty
    private double mediaNota;

    @JsonProperty
    private Set<AvaliacaoResponse> avaliacoes = new HashSet<>();

    @JsonProperty
    private Set<PerguntaListaResponse> perguntas = new HashSet<>();

    @JsonProperty
    private Set<CaracteristicaResponse> caracteristicas = new HashSet<>();

    @JsonProperty
    private Set<ImagemLinkResponse> imagens = new HashSet<>();

    public DetalhesProduto(Optional<Produto> produtoOpt){
        Produto produto = produtoOpt.get();
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        this.totalDeAvaliacoes = produto.getAvaliacao().size();
        this.mediaNota = produto.getAvaliacao().stream().mapToInt(avaliacao -> avaliacao.getNota()).average().orElse(0);

        this.avaliacoes = produto.getAvaliacao().stream().map(avaliacao -> new AvaliacaoResponse(avaliacao)).collect(Collectors.toSet());
        this.perguntas = produto.getPerguntas().stream().map(pergunta -> new PerguntaListaResponse(pergunta)).collect(Collectors.toSet());
        this.caracteristicas = produto.getCaracteristicas().stream().map(caracteristica -> new CaracteristicaResponse(caracteristica)).collect(Collectors.toSet());
        this.imagens = produto.getImagemList().stream().map(img -> new ImagemLinkResponse(img)).collect(Collectors.toSet());
    }

}
