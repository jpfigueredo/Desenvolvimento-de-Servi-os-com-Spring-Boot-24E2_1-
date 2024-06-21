package br.edu.infnet.dr4_at.repository;

import br.edu.infnet.dr4_at.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
