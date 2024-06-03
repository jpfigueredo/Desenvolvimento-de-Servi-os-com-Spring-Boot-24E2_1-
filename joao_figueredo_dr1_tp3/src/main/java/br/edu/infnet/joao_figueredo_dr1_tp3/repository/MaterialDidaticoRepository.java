package br.edu.infnet.joao_figueredo_dr1_tp3.repository;

import br.edu.infnet.joao_figueredo_dr1_tp3.entity.MaterialDidatico;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MaterialDidaticoRepository extends MongoRepository<MaterialDidatico, String> {
}
