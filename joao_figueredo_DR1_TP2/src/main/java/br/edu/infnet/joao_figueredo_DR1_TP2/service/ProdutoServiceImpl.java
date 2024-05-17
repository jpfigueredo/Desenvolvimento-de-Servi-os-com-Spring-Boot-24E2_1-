package br.edu.infnet.joao_figueredo_DR1_TP2.service;

import br.edu.infnet.joao_figueredo_DR1_TP2.domain.Produto;
import br.edu.infnet.joao_figueredo_DR1_TP2.dto.ProdutoDTO;
import br.edu.infnet.joao_figueredo_DR1_TP2.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public ProdutoDTO salvarProduto(ProdutoDTO produtoDTO) {
        Produto produto = modelMapper.map(produtoDTO, Produto.class);
        Produto savedProduto = produtoRepository.save(produto);
        return modelMapper.map(savedProduto, ProdutoDTO.class);
    }

    @Override
    public ProdutoDTO buscarProdutoPorId(Long id) throws Exception {
        Produto produto = produtoExistsByID(id);
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    @Override
    public List<ProdutoDTO> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(produto -> modelMapper.map(produto, ProdutoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO produtoDTO) throws Exception {
        produtoExistsByID(id);
        produtoDTO.setId(id);
        Produto updatedProduto = produtoRepository.save(modelMapper.map(produtoDTO, Produto.class));
        return modelMapper.map(updatedProduto, ProdutoDTO.class);
    }

    @Override
    public void deletarProduto(Long id) throws Exception {
        Produto produto = produtoExistsByID(id);
        produtoRepository.delete(produto);
    }

    private Produto produtoExistsByID(Long id) throws Exception {
        if (!produtoRepository.existsById(id)) {
            throw new Exception("Produto não encontrado com o ID: " + id);
        }
        return produtoRepository.findById(id).get();
    }
}
