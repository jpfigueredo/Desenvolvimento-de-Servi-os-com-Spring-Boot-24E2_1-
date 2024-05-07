package infnet.edu.br.joaofigueredodr1tp1.controller;

import infnet.edu.br.joaofigueredodr1tp1.dto.OperacaoDTO;
import infnet.edu.br.joaofigueredodr1tp1.service.OperacaoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/operacoes")
public class OperacaoController {

    @Autowired
    private OperacaoService operacaoService;

    @PostMapping("/adicao")
    @Transactional
    public ResponseEntity<Double> adicaoOperacao(@RequestBody @Valid OperacaoDTO operacaoDTO) {
        return new ResponseEntity<>(operacaoService.adicaoOperacao(operacaoDTO), HttpStatus.OK);
    }

    @PostMapping("/subtracao")
    @Transactional
    public ResponseEntity<Double> subtracaoOperacao(@RequestBody @Valid OperacaoDTO operacaoDTO) {
        return new ResponseEntity<>(operacaoService.adicaoOperacao(operacaoDTO), HttpStatus.OK);
    }

    @PostMapping("/multiplicacao")
    @Transactional
    public ResponseEntity<Double> multiplicacaoOperacao(@RequestBody @Valid OperacaoDTO operacaoDTO) {
        return new ResponseEntity<>(operacaoService.adicaoOperacao(operacaoDTO), HttpStatus.OK);
    }

    @PostMapping("/divisao")
    @Transactional
    public ResponseEntity<Double> divisaoOperacao(@RequestBody @Valid OperacaoDTO operacaoDTO) {
        return new ResponseEntity<>(operacaoService.adicaoOperacao(operacaoDTO), HttpStatus.OK);
    }

    @PostMapping("/exponenciacao")
    @Transactional
    public ResponseEntity<Double> exponenciacaoOperacao(@RequestBody @Valid OperacaoDTO operacaoDTO) {
        return new ResponseEntity<>(operacaoService.exponenciacaoOperacao(operacaoDTO), HttpStatus.OK);
    }
}