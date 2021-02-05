package br.com.zup.mercadoLivre.email;

import org.springframework.stereotype.Component;

@Component
public class EnvioEmailFake implements EnvioEmail {

    @Override
    public void enviarEmail(String nome, String de, String para, String assunto, String mensagem) {
        System.out.println("[ENVIO DE EMAIL: " +" \n" +
                "DE: "+de+" \n" +
                "PARA: "+para+" \n" +
                "MENSAGEM: mensagem de "+nome+" \n" + mensagem+" \n"+
                "]");
    }
}
