package br.edu.infnet.joao_figueredo_DR1_TP2.controller;

import br.edu.infnet.joao_figueredo_DR1_TP2.dto.ProdutoDTO;
import br.edu.infnet.joao_figueredo_DR1_TP2.service.ProdutoService;
import io.swagger.annotations.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
@Api(value = "ProdutoController", tags = {"Produtos"})
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @Transactional
    @ApiOperation(value = "Criar um novo produto")
    public ResponseEntity criarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return new ResponseEntity<>(produtoService.salvarProduto(produtoDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "Listar todos os produtos")
    public ResponseEntity listarProdutos() {
        return new ResponseEntity(produtoService.listarProdutos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar um produto por ID")
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@ApiParam(value = "ID do produto", required = true) @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(produtoService.buscarProdutoPorId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar um produto")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@ApiParam(value = "ID do produto", required = true) @PathVariable Long id,
                                                       @RequestBody ProdutoDTO produtoDTO) throws Exception {
        ProdutoDTO produtoUpdated = produtoService.atualizarProduto(id, produtoDTO);
        return new ResponseEntity<>(produtoUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar um produto")
    public ResponseEntity<Void> deletarProduto(@ApiParam(value = "ID do produto", required = true) @PathVariable Long id) throws Exception {
        produtoService.deletarProduto(id);
        return ResponseEntity.ok().build();
    }
}
