package br.edu.infnet.joao_figueredo_DR1_TP2.controller;

import br.edu.infnet.joao_figueredo_DR1_TP2.dto.ProdutoDTO;
import br.edu.infnet.joao_figueredo_DR1_TP2.service.ProdutoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @Transactional
    public ResponseEntity criarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return new ResponseEntity<>(produtoService.salvarProduto(produtoDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity listarProdutos() {
        return new ResponseEntity(produtoService.listarProdutos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(produtoService.buscarProdutoPorId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) throws Exception {
        ProdutoDTO produtoUpdated = produtoService.atualizarProduto(id, produtoDTO);
        return new ResponseEntity<>(produtoUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) throws Exception {
        produtoService.deletarProduto(id);
        return ResponseEntity.ok().build();
    }
}
