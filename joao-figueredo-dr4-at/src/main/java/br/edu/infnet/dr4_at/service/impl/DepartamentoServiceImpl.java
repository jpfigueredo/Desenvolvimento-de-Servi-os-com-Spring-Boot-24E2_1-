package br.edu.infnet.dr4_at.service.impl;

import br.edu.infnet.dr4_at.model.Departamento;
import br.edu.infnet.dr4_at.repository.DepartamentoRepository;
import br.edu.infnet.dr4_at.service.DepartamentoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoRepository repository;

    @Override
    public List<Departamento> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Departamento> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Departamento save(Departamento departamento) {
        return repository.save(departamento);
    }

    @Override
    public Departamento update(Long id, Departamento departamento) {
        ifEntityExists(id, Departamento.class);
        departamento.setId(id);
        return repository.save(departamento);
    }

    @Override
    public void delete(Long id) {
        Departamento departamento = getById(id).get();
        ifEntityExists(departamento.getId(), Departamento.class);
        repository.deleteById(departamento.getId());
    }

    public void ifEntityExists(Long id, Class<?> aClass) {
        StringBuilder sb = new StringBuilder();
        sb.append(aClass.getSimpleName()).append(" não encontrado.");
        if (!repository.existsById(id)) throw new EntityNotFoundException(sb.toString());
    }
}
