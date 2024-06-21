package br.edu.infnet.dr4_at.service.impl;

import br.edu.infnet.dr4_at.model.Funcionario;
import br.edu.infnet.dr4_at.repository.FuncionarioRepository;
import br.edu.infnet.dr4_at.service.FuncionarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Override
    public List<Funcionario> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Funcionario> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Funcionario save(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    @Override
    public Funcionario update(Long id, Funcionario funcionario) {
        ifEntityExists(id, Funcionario.class);
        funcionario.setId(id);
        return repository.save(funcionario);
    }

    @Override
    public void delete(Long id) {
        Funcionario funcionario = getById(id).get();
        ifEntityExists(funcionario.getId(), Funcionario.class);
        repository.deleteById(funcionario.getId());
    }

    public void ifEntityExists(Long id, Class<?> aClass) {
        StringBuilder sb = new StringBuilder();
        sb.append(aClass.getSimpleName()).append(" não encontrado.");
        if (!repository.existsById(id)) throw new EntityNotFoundException(sb.toString());
    }
}
