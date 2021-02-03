package br.com.zup.mercadoLivre.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncriptaSenha {

    public static String encripitar(String senha){
        PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
        return pwdEncoder.encode(senha);
    }
}
