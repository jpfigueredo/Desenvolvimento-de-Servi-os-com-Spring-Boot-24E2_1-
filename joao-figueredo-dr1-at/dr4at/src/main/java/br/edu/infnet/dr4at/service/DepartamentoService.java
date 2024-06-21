package br.edu.infnet.dr4at.service;

import br.edu.infnet.dr4at.model.Departamento;

import java.util.List;

public interface DepartamentoService {
    List<Departamento> getAll();

    Departamento getById(Long id);

    Departamento save(Departamento departamento);

    Departamento update(Long id, Departamento departamento);

    void delete(Long id);
}
