package br.edu.infnet.dr4_at.service;

import br.edu.infnet.dr4_at.model.Departamento;

import java.util.List;
import java.util.Optional;

public interface DepartamentoService {
    List<Departamento> getAll();

    Optional<Departamento> getById(Long id);

    Departamento save(Departamento departamento);

    Departamento update(Long id, Departamento departamento);

    void delete(Long id);
}
