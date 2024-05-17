package br.edu.infnet.joao_figueredo_DR1_TP2.repository;

import br.edu.infnet.joao_figueredo_DR1_TP2.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
