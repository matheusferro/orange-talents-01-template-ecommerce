package br.com.zup.mercadoLivre.avaliacao;

public class AvaliacaoResponse {

    private Integer nota;

    private String titulo;

    private String descricao;

    private String nomeProduto;

    private String usuario;

    public AvaliacaoResponse(Avaliacao avaliacao){
        this.nota = avaliacao.getNota();
        this.titulo = avaliacao.getTitulo();
        this.descricao = avaliacao.getDescricao();
        this.nomeProduto = avaliacao.getProduto().getNome();
        this.usuario = avaliacao.getUsuario().getEmail();
    }

}
