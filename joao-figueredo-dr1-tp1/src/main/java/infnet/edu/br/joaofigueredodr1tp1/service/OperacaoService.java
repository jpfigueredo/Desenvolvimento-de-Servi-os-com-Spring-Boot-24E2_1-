package infnet.edu.br.joaofigueredodr1tp1.service;

import infnet.edu.br.joaofigueredodr1tp1.dto.OperacaoDTO;
import org.springframework.stereotype.Service;

@Service
public interface OperacaoService {
    Double adicaoOperacao(OperacaoDTO operacaoDTO);

    Double subtracaoOperacao(OperacaoDTO operacaoDTO);

    Double multiplicacaoOperacao(OperacaoDTO operacaoDTO);

    Double divisaoOperacao(OperacaoDTO operacaoDTO);

    Double exponenciacaoOperacao(OperacaoDTO operacaoDTO);
}