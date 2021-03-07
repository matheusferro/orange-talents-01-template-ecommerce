package br.com.zup.mercadoLivre.compra;

import org.springframework.web.util.UriComponentsBuilder;

public enum GetwayPagamento {
    PAGSEGURO{
        @Override
        public String getUrl(Compra compra, UriComponentsBuilder uri) {
            return "pagseguro.com?returnId="+compra.getId()+"&redirectUrl="+
                    uri.path("/pagseguro-retorno/{idCompra}").buildAndExpand(compra.getId()).toUri();

        }
    },
    PAYPAL {
        @Override
        public String getUrl(Compra compra, UriComponentsBuilder uri) {
            return "paypal.com/"+compra.getId()+"?redirectUrl="+
                    uri.path("/paypal-retorno/{idCompra}").buildAndExpand(compra.getId()).toUri();
        }
    };

    /**
     * Refatorado após sugestão do Yuri Matheus.
     * Melhorado para futuras adições de métodos de pagamento.
     *
     * @param compra    <--- Objeto compra.
     * @param uri       <--- UriComponentsBuilder Para compor a urlDeRetorno.
     */
    public abstract String getUrl(Compra compra, UriComponentsBuilder uri);
}
