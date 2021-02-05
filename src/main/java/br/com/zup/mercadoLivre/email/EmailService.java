package br.com.zup.mercadoLivre.email;

import br.com.zup.mercadoLivre.pergunta.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private EnvioEmail email;

    public void novaPergunta(Pergunta pergunta){
        email.enviarEmail(pergunta.getUsuario().getEmail(), "email@empresa.com", pergunta.getEmailDono(), "Nova mensagem", pergunta.getTitulo());
    }
}
