package br.com.zup.mercadoLivre.security;

import br.com.zup.mercadoLivre.usuario.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secretKey;


    public String gerarToken(Authentication authentication){
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date expDate = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API ML")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean isTokenValido(String token){
        try {
            Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            //e.printStackTrace();
            return false;
        }
    }

    public Long getIdUsuario(String token){
        Claims claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
