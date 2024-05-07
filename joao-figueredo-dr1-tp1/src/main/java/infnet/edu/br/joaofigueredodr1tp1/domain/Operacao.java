package infnet.edu.br.joaofigueredodr1tp1.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Operacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double num1;
    private Double num2;
}
