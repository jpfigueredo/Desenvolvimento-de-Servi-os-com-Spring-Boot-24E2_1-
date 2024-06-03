package br.edu.infnet.joao_figueredo_dr1_tp3.service;

import br.edu.infnet.joao_figueredo_dr1_tp3.entity.MaterialDidatico;
import br.edu.infnet.joao_figueredo_dr1_tp3.repository.MaterialDidaticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialDidaticoService {

    @Autowired
    private MaterialDidaticoRepository repository;

    public List<MaterialDidatico> findAll() {
        return repository.findAll();
    }

    public Optional<MaterialDidatico> findById(String id) {
        return repository.findById(id);
    }

    public MaterialDidatico save(MaterialDidatico materialDidatico) {
        return repository.save(materialDidatico);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
