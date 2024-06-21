package br.edu.infnet.dr4at.service.impl;

import br.edu.infnet.dr4at.model.Usuario;
import br.edu.infnet.dr4at.repository.UsuarioRepository;
import br.edu.infnet.dr4at.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> getAll() {
        return repository.findAll();
    }

    @Override
    public Usuario getById(String id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado."));
    }

    @Override
    public Usuario save(Usuario usuario) {
        if (repository.findUsuarioByEmail(usuario.getEmail()) != null) {
            throw new IllegalArgumentException("Email já está em uso!");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    @Override
    public Usuario update(String id, Usuario usuario) {
        ifEntityExists(id);
        if (!usuario.getSenha().isEmpty()) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        usuario.setId(id);
        return repository.save(usuario);
    }

    @Override
    public void delete(String id) {
        ifEntityExists(id);
        repository.deleteById(id);
    }

    private void ifEntityExists(String id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Usuario não encontrado.");
        }
    }
}
