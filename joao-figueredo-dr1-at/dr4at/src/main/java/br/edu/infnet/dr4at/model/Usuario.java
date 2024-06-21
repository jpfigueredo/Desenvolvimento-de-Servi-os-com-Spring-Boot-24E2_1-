package br.edu.infnet.dr4at.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    private String id;
    private String nome;
    private String email;
    private String senha;
    private String papel;
}
