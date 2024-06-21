package br.edu.infnet.dr4_at.service.impl;

import br.edu.infnet.dr4_at.model.Usuario;
import br.edu.infnet.dr4_at.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findUsuarioByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não cadastrado!");
        }
        return new User(usuario.getNome(), usuario.getSenha(),
                Collections.singletonList(new SimpleGrantedAuthority(usuario.getPapel())));
    }
}
