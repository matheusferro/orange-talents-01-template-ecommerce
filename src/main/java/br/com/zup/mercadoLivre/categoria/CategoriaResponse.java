package br.com.zup.mercadoLivre.categoria;

public class CategoriaResponse {

    private Long id;

    private String nome;

    /**
     * TODO: PARA IMPLEMENTAÇÃO CORRETA DEVE SER MUDADO O TIPO DA VARIAVEL A SEGUIR
     */
    private Categoria categoriaMae;

    public CategoriaResponse(Long id, String nome, Categoria categoriaMae) {
        this.id = id;
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }
}
