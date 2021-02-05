package br.com.zup.mercadoLivre.email;

public interface EnvioEmail {

    /**
     *
     * @param from
     * @param to
     * @param subject
     * @param message
     */
    public void enviarEmail(String nameFrom, String from, String to, String subject, String message);
}
