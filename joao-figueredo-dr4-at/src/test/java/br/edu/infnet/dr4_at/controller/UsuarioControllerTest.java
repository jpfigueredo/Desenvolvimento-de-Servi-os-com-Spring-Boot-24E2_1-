package br.edu.infnet.dr4_at.controller;

import br.edu.infnet.dr4_at.model.Usuario;
import br.edu.infnet.dr4_at.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    private Usuario usuario1;
    private Usuario usuario2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario1 = new Usuario("1", "admin", "admin@admin.com", "senha123", "ADMIN");
        usuario2 = new Usuario("2", "user", "user@user.com", "senha123", "USER");
    }

    @Test
    @DisplayName("Deve retornar todos os usuários")
    void testGetAllUsuarios() {
        when(usuarioService.getAll()).thenReturn(List.of(usuario1, usuario2));
        ResponseEntity<Usuario[]> response = restTemplate.getForEntity("/api/auth", Usuario[].class);
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);
    }

    @Test
    @DisplayName("Deve retornar um usuário pelo ID")
    void testGetUsuarioById() {
        when(usuarioService.getById("1")).thenReturn(java.util.Optional.of(usuario1));
        ResponseEntity<Usuario> response = restTemplate.getForEntity("/api/auth/1", Usuario.class);
        assertNotNull(response.getBody());
        assertEquals("admin", response.getBody().getNome());
    }

    @Test
    @DisplayName("Deve criar um novo usuário")
    void testCreateUsuario() {
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario1);
        ResponseEntity<Usuario> response = restTemplate.postForEntity("/api/auth", usuario1, Usuario.class);
        assertNotNull(response.getBody());
        assertEquals("admin", response.getBody().getNome());
    }

    @Test
    @DisplayName("Deve atualizar um usuário existente")
    void testUpdateUsuario() {
        when(usuarioService.update(anyString(), any(Usuario.class))).thenReturn(usuario1);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Usuario> entity = new HttpEntity<>(usuario1, headers);
        ResponseEntity<Usuario> response = restTemplate.exchange("/api/auth", HttpMethod.PUT, entity, Usuario.class);
        assertNotNull(response.getBody());
        assertEquals("admin", response.getBody().getNome());
    }

    @Test
    @DisplayName("Deve deletar um usuário pelo ID")
    void testDeleteUsuario() {
        doNothing().when(usuarioService).delete("1");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<Void> response = restTemplate.exchange("/api/auth/1", HttpMethod.DELETE, entity, Void.class);
        assertEquals(204, response.getStatusCodeValue());
    }
}
