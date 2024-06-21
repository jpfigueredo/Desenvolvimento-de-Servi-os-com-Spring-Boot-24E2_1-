package br.edu.infnet.dr4at.service.impl;

import br.edu.infnet.dr4at.model.Departamento;
import br.edu.infnet.dr4at.repository.DepartamentoRepository;
import br.edu.infnet.dr4at.service.DepartamentoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoRepository repository;

    @Override
    public List<Departamento> getAll() {
        return repository.findAll();
    }

    @Override
    public Departamento getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Departamento não encontrado."));
    }

    @Override
    public Departamento save(Departamento departamento) {
        return repository.save(departamento);
    }

    @Override
    public Departamento update(Long id, Departamento departamento) {
        ifEntityExists(id);
        departamento.setId(id);
        return repository.save(departamento);
    }

    @Override
    public void delete(Long id) {
        ifEntityExists(id);
        repository.deleteById(id);
    }

    private void ifEntityExists(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Departamento não encontrado.");
        }
    }
}
