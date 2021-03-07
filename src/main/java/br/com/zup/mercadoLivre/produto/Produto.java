package br.com.zup.mercadoLivre.produto;

import br.com.zup.mercadoLivre.avaliacao.Avaliacao;
import br.com.zup.mercadoLivre.categoria.Categoria;
import br.com.zup.mercadoLivre.pergunta.Pergunta;
import br.com.zup.mercadoLivre.produto.caracteristica.Caracteristica;
import br.com.zup.mercadoLivre.produto.caracteristica.CaracteristicaRequest;
import br.com.zup.mercadoLivre.produto.imagem.Imagem;
import br.com.zup.mercadoLivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @Positive
    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private Integer quantidade;

    @Size(min=3)
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    @NotBlank
    @Column(nullable = false)
    @Length(max=1000)
    private String descricao;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Imagem> imagemList = new HashSet<>();

    @OneToMany(mappedBy = "produto", fetch = FetchType.EAGER)
    private Set<Avaliacao> avaliacao = new HashSet<>();

    @OneToMany(mappedBy = "produto", fetch = FetchType.EAGER)
    private Set<Pergunta> perguntas = new HashSet<>();

    @Deprecated
    public Produto(){}

    public Produto(@NotBlank String nome,
                   @Positive BigDecimal valor,
                   @Min(1) Integer quantidade,
                   @NotBlank @Length(max = 1000) String descricao,
                   @NotNull @Valid Categoria categoria,
                   @NotNull @Valid Usuario usuario,
                   @NotNull @Valid Set<CaracteristicaRequest> caracteristicas) {

        notNull(usuario, "Não é possível cadastrar um produto não relacionado com um usuário.");
        notNull(categoria, "É necessário escolher uma categoria.");
        isTrue(caracteristicas.size() >= 3, "É necessário definir no minimo 3 caracteristicas.");
        notNull(quantidade, "É necessário definir uma quantidade.");
        isTrue(quantidade > 0, "Quantidade inválida.");

        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
        Set<Caracteristica> setCaracteristicas = caracteristicas.stream()
                .map(carac -> carac.toModel(this))
                .collect(Collectors.toSet());

        this.caracteristicas.addAll(setCaracteristicas);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Set<Imagem> getImagemList() {
        return imagemList;
    }

    public Set<Avaliacao> getAvaliacao() {
        return avaliacao;
    }

    public Set<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void adicionarImagens(Set<String> links) {
        notNull(links, "Links invalidos.");
        Set<Imagem> imagens = links.stream().map(link ->
                new Imagem(link, this)
                ).collect(Collectors.toSet());
        this.imagemList.addAll(imagens);
    }

    public boolean produtoPertenceUsuario(Usuario usuario){
        notNull(usuario, "Usuario invalido.");
        return this.usuario.getId() == usuario.getId();
    }

    public boolean abaterQuantidade(int quantidadeComprada){
        notNull(quantidadeComprada, "Não é possível abater esse valor.");
        assertTrue(quantidadeComprada>0, "Quantidade deve ser positiva.");
        if(quantidadeComprada <= this.quantidade){
            this.quantidade -= quantidadeComprada;
            return true;
        }
        return false;
    }
}
