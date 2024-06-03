package br.edu.infnet.joao_figueredo_dr1_tp3.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class MaterialDidatico {

    @Id
    private String id;
    private String titulo;
    private String descricao;

    // Lombok Getters and Setters
}
