package br.com.zup.mercadoLivre.security;

import br.com.zup.mercadoLivre.usuario.Usuario;
import br.com.zup.mercadoLivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario>  optUsuario = usuarioRepository.findByEmail(username);
        if (optUsuario.isPresent()){
            return optUsuario.get();
        }
        throw new UsernameNotFoundException("Dados invalidos.");
    }
}
