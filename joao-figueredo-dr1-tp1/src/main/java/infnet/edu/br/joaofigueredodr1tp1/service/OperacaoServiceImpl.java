package infnet.edu.br.joaofigueredodr1tp1.service;

import infnet.edu.br.joaofigueredodr1tp1.domain.Operacao;
import infnet.edu.br.joaofigueredodr1tp1.dto.OperacaoDTO;
import infnet.edu.br.joaofigueredodr1tp1.repository.IOperacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperacaoServiceImpl implements OperacaoService{

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private IOperacaoRepository operacaoRepository;
    
    @Override
    public OperacaoDTO createOperacao(OperacaoDTO operacaoDTO) {
        Operacao operacao = modelMapper.map(operacaoDTO, Operacao.class);
        Operacao savedOperacao = operacaoRepository.save(operacao);
        return modelMapper.map(savedOperacao, OperacaoDTO.class);
    }

    @Override
    public OperacaoDTO getOperacaoById(Long operacaoId) {
        Operacao operacao = operacaoExistsByID(operacaoId);
        return modelMapper.map(operacao, OperacaoDTO.class);
    }

    @Override
    public OperacaoDTO updateOperacao(Long operacaoId, OperacaoDTO operacaoDTO) {
        if (!operacaoRepository.existsById(operacaoId)) {
            throw new EntityNotFoundException("Imóvel não encontrado com o ID: " + operacaoId);
        }
        operacaoDTO.setId(operacaoId);
        Operacao updatedOperacao = operacaoRepository.save(modelMapper.map(operacaoDTO, Operacao.class));
        return modelMapper.map(updatedOperacao, OperacaoDTO.class);
    }

    @Override
    public void deleteOperacao(Long operacaoId) {
        Operacao operacao = operacaoExistsByID(operacaoId);
        operacaoRepository.delete(operacao);
    }

    @Override
    public List<OperacaoDTO> getOperacaoList() {
        List<Operacao> operacaoList = operacaoRepository.findAll();
        return operacaoList.stream()
                .map(operacao -> modelMapper.map(operacao, OperacaoDTO.class))
                .collect(Collectors.toList());
    }

    private Operacao operacaoExistsByID(Long operacaoId) {
        if (!operacaoRepository.existsById(operacaoId)) {
            throw new EntityNotFoundException("Imóvel não encontrado com o ID: " + operacaoId);
        }
        return operacaoRepository.findById(operacaoId).get();
    }


}
