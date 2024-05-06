package infnet.edu.br.joaofigueredodr1tp1.controller;

import infnet.edu.br.joaofigueredodr1tp1.dto.OperacaoDTO;
import infnet.edu.br.joaofigueredodr1tp1.service.OperacaoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/operacoes")
public class OperacaoController {

    @Autowired
    private OperacaoService operacaoService;

    @PostMapping
    @Transactional
    public ResponseEntity<OperacaoDTO> createOperacao(@RequestBody @Valid OperacaoDTO operacaoDTO) {
        return new ResponseEntity<>(operacaoService.createOperacao(operacaoDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OperacaoDTO>> getProperties() {
        return new ResponseEntity<>(operacaoService.getOperacaoList(), HttpStatus.OK);
    }

    @GetMapping("/{operacaoId}")
    public ResponseEntity<OperacaoDTO> getOperacaoById(@PathVariable Long operacaoId) {
        return new ResponseEntity<>(operacaoService.getOperacaoById(operacaoId), HttpStatus.OK);
    }

    @PutMapping("/{operacaoId}")
    public ResponseEntity<OperacaoDTO> updateOperacao(@PathVariable Long operacaoId, @RequestBody OperacaoDTO operacaoDto) {
        OperacaoDTO updatedOperacao = operacaoService.updateOperacao(operacaoId, operacaoDto);
        return new ResponseEntity<>(updatedOperacao, HttpStatus.OK);
    }

    @DeleteMapping("/{operacaoId}")
    public void deleteOperacao(@PathVariable Long operacaoId) {
        operacaoService.deleteOperacao(operacaoId);
    }

}