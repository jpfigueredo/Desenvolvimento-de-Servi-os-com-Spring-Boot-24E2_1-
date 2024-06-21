package br.edu.infnet.dr4_at.service.impl;

import br.edu.infnet.dr4_at.model.Departamento;
import br.edu.infnet.dr4_at.model.Usuario;
import br.edu.infnet.dr4_at.repository.UsuarioRepository;
import br.edu.infnet.dr4_at.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public List<Usuario> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Usuario> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public Usuario update(String id, Usuario usuario) {
        ifEntityExists(id, Departamento.class);
        usuario.setId(id);
        return repository.save(usuario);
    }

    @Override
    public void delete(String id) {
        Usuario usuario = getById(id).get();
        ifEntityExists(usuario.getId(), Departamento.class);
        repository.deleteById(usuario.getId());
    }

    public void ifEntityExists(String id, Class<?> aClass) {
        StringBuilder sb = new StringBuilder();
        sb.append(aClass.getSimpleName()).append(" não encontrado.");
        if (!repository.existsById(id)) throw new EntityNotFoundException(sb.toString());
    }
}
