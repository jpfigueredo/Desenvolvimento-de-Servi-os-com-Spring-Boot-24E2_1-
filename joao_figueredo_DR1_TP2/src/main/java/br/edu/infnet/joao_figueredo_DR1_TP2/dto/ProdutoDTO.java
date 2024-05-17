package br.edu.infnet.joao_figueredo_DR1_TP2.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Data
public class ProdutoDTO {
    @NotNull(message = "O ID do Produto é obrigatório!")
    private Long id;

    @NotNull(message = "O nome do Produto é obrigatorio!")
    private String nome;

    @NotNull(message = "O preco do Produto é obrigatorio!")
    @Min(value = 1, message = "O preco mínimo deve ser 1")
    @Max(value = 100000, message = "O preco máximo deve ser 100.000")
    private Double preco;

    private String descricao;
}
