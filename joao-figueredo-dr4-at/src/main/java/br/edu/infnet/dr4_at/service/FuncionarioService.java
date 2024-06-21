package br.edu.infnet.dr4_at.service;

import br.edu.infnet.dr4_at.model.Funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioService {
    List<Funcionario> getAll();

    Optional<Funcionario> getById(Long id);

    Funcionario save(Funcionario funcionario);

    Funcionario update(Long id, Funcionario funcionario);

    void delete(Long id);
}
