package br.edu.infnet.dr4_at.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    public Funcionario(Long id, String nome, String endereco, String telefone, String email, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }
}
