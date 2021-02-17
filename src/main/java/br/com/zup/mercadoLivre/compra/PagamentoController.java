package br.com.zup.mercadoLivre.compra;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagamentoController {


    @PostMapping("paypal-retorno/{id}")
    public void metodoPaypal(){

    }
    @PostMapping("pagseguro-retorno/{id}")
    public void metodoPagseguro(){

    }

}
