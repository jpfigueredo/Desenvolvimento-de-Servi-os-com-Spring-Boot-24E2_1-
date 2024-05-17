package br.edu.infnet.joao_figueredo_DR1_TP2.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="property_id")
    @EqualsAndHashCode.Include
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;

}
