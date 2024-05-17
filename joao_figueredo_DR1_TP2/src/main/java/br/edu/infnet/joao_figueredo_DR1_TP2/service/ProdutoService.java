package br.edu.infnet.joao_figueredo_DR1_TP2.service;

import br.edu.infnet.joao_figueredo_DR1_TP2.dto.ProdutoDTO;

import java.util.List;

public interface ProdutoService {
    ProdutoDTO salvarProduto(ProdutoDTO produtoDTO);
    List<ProdutoDTO> listarProdutos();
    ProdutoDTO buscarProdutoPorId(Long id) throws Exception;
    ProdutoDTO atualizarProduto(Long id, ProdutoDTO produtoDTO) throws Exception;
    void deletarProduto(Long id) throws Exception;
}