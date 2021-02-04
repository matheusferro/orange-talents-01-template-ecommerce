package br.com.zup.mercadoLivre.produto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class CaracteristicaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CaracteristicaRequest(@NotBlank @JsonProperty String nome, @NotBlank @JsonProperty String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Caracteristica toModel(@NotNull @Valid Produto produto){
        return new Caracteristica(nome, descricao, produto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicaRequest that = (CaracteristicaRequest) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
