package br.com.zup.mercadoLivre.produto;

import br.com.zup.mercadoLivre.categoria.Categoria;
import br.com.zup.mercadoLivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Positive
    @Column(nullable = false)
    private Integer quantidade;

    @Size(min=3)
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
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

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Imagem> imagemList = new HashSet<>();

    @Deprecated
    public Produto(){}

    public Produto(@NotBlank String nome,
                   @Positive BigDecimal valor,
                   @Positive Integer quantidade,
                   @NotBlank @Length(max = 1000) String descricao,
                   @NotNull @Valid Categoria categoria,
                   @NotNull @Valid Usuario usuario,
                   @NotNull @Valid Set<CaracteristicaRequest> caracteristicas) {

        notNull(usuario, "Não é possível cadastrar um produto não relacionado com um usuário.");
        notNull(categoria, "É necessário escolher uma categoria.");
        isTrue(caracteristicas.size() >= 3, "É necessário definir no minimo 3 caracteristicas.");

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

    public void adicionarImagens(Set<String> links) {
        notNull(links, "Links invalidos.");
        Set<Imagem> imagens = links.stream().map(link ->
                new Imagem(link, this)
                ).collect(Collectors.toSet());
        this.imagemList.addAll(imagens);
    }
}
