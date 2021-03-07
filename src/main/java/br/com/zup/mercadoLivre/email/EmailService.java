package br.com.zup.mercadoLivre.email;

import br.com.zup.mercadoLivre.compra.Compra;
import br.com.zup.mercadoLivre.pergunta.Pergunta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EmailService {

    @Autowired
    private EnvioEmail email;

    private Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void novaPergunta(Pergunta pergunta){
        email.enviarEmail(pergunta.getUsuario().getEmail(), "email@empresa.com", pergunta.getEmailDono(), "Nova mensagem", pergunta.getTitulo());
    }

    /**
     * Implementando thread para envio de e-mail.
     * TODO: verificar um jeito melhor para não ter que implementar a thread em todo método.
     *
     * @param compra
     */
    public void compraRealizada(Compra compra) {

        ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
        emailExecutor.execute(() -> {
            try {
                email.enviarEmail(compra.getUsuario().getEmail(),
                        "email@empresa.com",
                        compra.getProduto().getUsuario().getEmail(),
                        "Compra realizada",
                        "compra");
            } catch (Exception e) {
                logger.info("Erro ao enviar o email de compra.", e);
            }
        });
        emailExecutor.shutdown();
    }
}
