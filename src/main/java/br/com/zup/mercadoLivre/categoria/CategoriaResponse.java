package br.com.zup.mercadoLivre.categoria;

public class CategoriaResponse {

    private Long id;

    private String nome;

    private CategoriaMaeResponse categoriaMae;

    public CategoriaResponse(Long id, String nome, Categoria categoriaMae) {
        this.id = id;
        this.nome = nome;
        this.categoriaMae = new CategoriaMaeResponse(categoriaMae);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public CategoriaMaeResponse getCategoriaMae() {
        return categoriaMae;
    }
}
