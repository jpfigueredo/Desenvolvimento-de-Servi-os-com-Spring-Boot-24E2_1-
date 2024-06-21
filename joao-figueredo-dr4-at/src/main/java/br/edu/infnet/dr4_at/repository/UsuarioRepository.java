package br.edu.infnet.dr4_at.repository;

import br.edu.infnet.dr4_at.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Usuario findUsuarioByEmail(String email);
}
