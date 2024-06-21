package br.edu.infnet.dr4at;

import br.edu.infnet.dr4at.model.Departamento;
import br.edu.infnet.dr4at.model.Funcionario;
import br.edu.infnet.dr4at.model.Usuario;
import br.edu.infnet.dr4at.service.DepartamentoService;
import br.edu.infnet.dr4at.service.FuncionarioService;
import br.edu.infnet.dr4at.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Departamento d1 = new Departamento(null, "TI", "Andar 1", null);
        Departamento d2 = new Departamento(null, "RH", "Andar 2", null);
        departamentoService.save(d1);
        departamentoService.save(d2);

        Funcionario f1 = new Funcionario(null, "test", "test street", "789456123", "test@test.com", LocalDate.of(1980, 1, 1), d1);
        Funcionario f2 = new Funcionario(null, "test2", "test avenue", "123456789", "test2@test.com", LocalDate.of(1990, 2, 2), d2);
        funcionarioService.save(f1);
        funcionarioService.save(f2);

        Usuario user = new Usuario(null, "admin", "admin@admin.com", "password", "ROLE_ADMIN");
        usuarioService.save(user);

    }
}