package br.edu.infnet.dr4at.repository;

import br.edu.infnet.dr4at.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Usuario findUsuarioByEmail(String email);
}
