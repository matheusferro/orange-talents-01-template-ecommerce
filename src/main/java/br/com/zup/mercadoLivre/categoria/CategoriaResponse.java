package br.com.zup.mercadoLivre.categoria;

public class CategoriaResponse {

    private Long id;

    private String nome;

    private Long idCategoriaMae;

    public CategoriaResponse(Long id, String nome, Long idCategoriaMae) {
        this.id = id;
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }
}
