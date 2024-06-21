package br.edu.infnet.dr4at.service;

import br.edu.infnet.dr4at.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> getAll();

    Usuario getById(String id);

    Usuario save(Usuario usuario);

    Usuario update(String id, Usuario usuario);

    void delete(String id);
}
