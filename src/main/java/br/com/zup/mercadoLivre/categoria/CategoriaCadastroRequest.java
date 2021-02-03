package br.com.zup.mercadoLivre.categoria;

import br.com.zup.mercadoLivre.anotacao.ExistsValue;
import br.com.zup.mercadoLivre.anotacao.UniqueValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Optional;

public class CategoriaCadastroRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @Positive
    @ExistsValue(domainClass = Categoria.class, fieldName = "id", nullable = true)
    private Long idCategoriaMae;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CategoriaCadastroRequest(@NotBlank @JsonProperty("nome") String nome,
                                    @Positive @JsonProperty("idCategoriaMae") Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria toModel(CategoriaRepository repository){
        Categoria categoriaMae = null;
        if(idCategoriaMae != null){
            categoriaMae = repository.findById(idCategoriaMae).orElse(null);
        }
        return new Categoria(nome, categoriaMae);
    }

    @Override
    public String toString() {
        return "CategoriaCadastroRequest{" +
                "categoriaNome='" + nome + '\'' +
                ", categoriaMae='" + idCategoriaMae + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }
}
