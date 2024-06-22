package br.edu.infnet.dr4at;

import br.edu.infnet.dr4at.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Dr4atApplicationTests {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @BeforeEach
    void setUp() {
        usuarioRepository.deleteAll();
    }

    @Test
    void contextLoads() {
    }

}
