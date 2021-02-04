package br.com.zup.mercadoLivre.produto;

import br.com.zup.mercadoLivre.categoria.Categoria;
import br.com.zup.mercadoLivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
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
    @ManyToOne
    private Categoria categoria;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    public Produto(@NotBlank String nome,
                   @Positive BigDecimal valor,
                   @Positive Integer quantidade,
                   @NotBlank @Length(max = 1000) String descricao,
                   @NotNull @Valid Categoria categoria,
                   @NotNull @Valid Usuario usuario,
                   @NotNull @Valid Set<CaracteristicaRequest> caracteristicas) {

        notNull(usuario, "Não é possível cadastrar um produto não relacionado com um usuário.");
        notNull(categoria, "É necessário escolher uma categoria.");
        isTrue(this.caracteristicas.size() <= 3, "É necessário definir no minimo 3 caracteristicas.");

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
}
