package infnet.edu.br.joaofigueredodr1tp1.service;

import infnet.edu.br.joaofigueredodr1tp1.dto.OperacaoDTO;
import infnet.edu.br.joaofigueredodr1tp1.repository.IOperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperacaoServiceImpl implements OperacaoService {

    @Autowired
    private IOperacaoRepository operacaoRepository;

    @Override
    public Double adicaoOperacao(OperacaoDTO operacaoDTO) {
        return operacaoDTO.getNum1() + operacaoDTO.getNum2();
    }

    @Override
    public Double subtracaoOperacao(OperacaoDTO operacaoDTO) {
        return operacaoDTO.getNum1() - operacaoDTO.getNum2();
    }

    @Override
    public Double multiplicacaoOperacao(OperacaoDTO operacaoDTO) {
        return operacaoDTO.getNum1() * operacaoDTO.getNum2();
    }

    @Override
    public Double divisaoOperacao(OperacaoDTO operacaoDTO) {
        return operacaoDTO.getNum1() / operacaoDTO.getNum2();
    }

    @Override
    public Double exponenciacaoOperacao(OperacaoDTO operacaoDTO) {
        return Math.pow(operacaoDTO.getNum1(), operacaoDTO.getNum2());
    }

}
