package br.edu.infnet.joao_figueredo_dr1_tp3;

import br.edu.infnet.joao_figueredo_dr1_tp3.entity.Aluno;
import br.edu.infnet.joao_figueredo_dr1_tp3.entity.Curso;
import br.edu.infnet.joao_figueredo_dr1_tp3.repository.AlunoRepository;
import br.edu.infnet.joao_figueredo_dr1_tp3.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public void run(String... args) throws Exception {
        Curso curso1 = new Curso();
        curso1.setNome("Matemática");
        cursoRepository.save(curso1);

        Curso curso2 = new Curso();
        curso2.setNome("História");
        cursoRepository.save(curso2);

        Aluno aluno1 = new Aluno();
        aluno1.setNome("João");
        aluno1.getCursos().add(curso1);
        aluno1.getCursos().add(curso2);
        alunoRepository.save(aluno1);

        Aluno aluno2 = new Aluno();
        aluno2.setNome("Maria");
        aluno2.getCursos().add(curso1);
        alunoRepository.save(aluno2);
    }
}