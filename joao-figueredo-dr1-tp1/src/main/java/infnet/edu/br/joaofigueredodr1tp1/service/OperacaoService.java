package infnet.edu.br.joaofigueredodr1tp1.service;

import infnet.edu.br.joaofigueredodr1tp1.dto.OperacaoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OperacaoService {
    OperacaoDTO createOperacao(OperacaoDTO operacaoDTO);
    OperacaoDTO getOperacaoById(Long id);
    List<OperacaoDTO> getOperacaoList();
    OperacaoDTO updateOperacao(Long operacaoId, OperacaoDTO operacaoDTO);
    void deleteOperacao(Long operacaoId);

}