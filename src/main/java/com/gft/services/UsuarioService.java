package com.gft.services;

import com.gft.entities.Usuario;
import com.gft.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> optional = usuarioRepository.findByUsername(username);

        if (optional.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        return optional.get();
    }


    public Usuario buscarUsuarioPorId(Long id) {

        Optional<Usuario> optional = usuarioRepository.findById(id);

        if (optional.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }
        return optional.get();
    }
}
