package br.edu.infnet.dr4at.service.impl;

import br.edu.infnet.dr4at.model.Funcionario;
import br.edu.infnet.dr4at.repository.FuncionarioRepository;
import br.edu.infnet.dr4at.service.FuncionarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Override
    public List<Funcionario> getAll() {
        return repository.findAll();
    }

    @Override
    public Funcionario getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Funcionario não encontrado."));
    }

    @Override
    public Funcionario save(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    @Override
    public Funcionario update(Long id, Funcionario funcionario) {
        ifEntityExists(id);
        funcionario.setId(id);
        return repository.save(funcionario);
    }

    @Override
    public void delete(Long id) {
        ifEntityExists(id);
        repository.deleteById(id);
    }

    private void ifEntityExists(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Funcionario não encontrado.");
        }
    }
}
