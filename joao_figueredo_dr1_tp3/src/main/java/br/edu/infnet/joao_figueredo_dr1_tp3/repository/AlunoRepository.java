package br.edu.infnet.joao_figueredo_dr1_tp3.repository;

import br.edu.infnet.joao_figueredo_dr1_tp3.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
