package infnet.edu.br.joaofigueredodr1tp1.repository;

import infnet.edu.br.joaofigueredodr1tp1.domain.Operacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOperacaoRepository  extends JpaRepository<Operacao, Long> {
}