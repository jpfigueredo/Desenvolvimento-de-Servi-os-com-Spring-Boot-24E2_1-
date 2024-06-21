package br.edu.infnet.dr4_at.service;

import br.edu.infnet.dr4_at.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> getAll();

    Optional<Usuario> getById(String id);

    Usuario save(Usuario usuario);

    Usuario update(String id, Usuario usuario);

    void delete(String id);
}
