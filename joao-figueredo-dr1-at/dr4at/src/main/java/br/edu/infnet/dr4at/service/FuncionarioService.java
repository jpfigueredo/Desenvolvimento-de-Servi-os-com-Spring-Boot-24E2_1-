package br.edu.infnet.dr4at.service;

import br.edu.infnet.dr4at.model.Funcionario;

import java.util.List;

public interface FuncionarioService {
    List<Funcionario> getAll();

    Funcionario getById(Long id);

    Funcionario save(Funcionario funcionario);

    Funcionario update(Long id, Funcionario funcionario);

    void delete(Long id);
}
