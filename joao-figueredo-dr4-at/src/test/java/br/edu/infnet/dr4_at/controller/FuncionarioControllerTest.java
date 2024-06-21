package br.edu.infnet.dr4_at.controller;

import br.edu.infnet.dr4_at.model.Funcionario;
import br.edu.infnet.dr4_at.service.FuncionarioService;
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

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class FuncionarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private FuncionarioService funcionarioService;

    @InjectMocks
    private FuncionarioController funcionarioController;

    private Funcionario funcionario1;
    private Funcionario funcionario2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        funcionario1 = new Funcionario(1L, "João Silva", "Rua A, 123", "111111111", "joao@example.com", LocalDate.parse("1990-01-01"));
        funcionario2 = new Funcionario(2L, "Maria Oliveira", "Rua B, 456", "222222222", "maria@example.com", LocalDate.parse("1985-05-05"));
    }

    @Test
    @DisplayName("Deve retornar todos os funcionários")
    void testGetAllFuncionarios() {
        when(funcionarioService.getAll()).thenReturn(List.of(funcionario1, funcionario2));
        ResponseEntity<Funcionario[]> response = restTemplate.getForEntity("/funcionarios", Funcionario[].class);
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);
    }

    @Test
    @DisplayName("Deve retornar um funcionário pelo ID")
    void testGetFuncionarioById() {
        when(funcionarioService.getById(1L)).thenReturn(java.util.Optional.of(funcionario1));
        ResponseEntity<Funcionario> response = restTemplate.getForEntity("/funcionarios/1", Funcionario.class);
        assertNotNull(response.getBody());
        assertEquals("João Silva", response.getBody().getNome());
    }

    @Test
    @DisplayName("Deve criar um novo funcionário")
    void testCreateFuncionario() {
        when(funcionarioService.save(any(Funcionario.class))).thenReturn(funcionario1);
        ResponseEntity<Funcionario> response = restTemplate.postForEntity("/funcionarios", funcionario1, Funcionario.class);
        assertNotNull(response.getBody());
        assertEquals("João Silva", response.getBody().getNome());
    }

    @Test
    @DisplayName("Deve atualizar um funcionário existente")
    void testUpdateFuncionario() {
        when(funcionarioService.update(anyLong(), any(Funcionario.class))).thenReturn(funcionario1);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Funcionario> entity = new HttpEntity<>(funcionario1, headers);
        ResponseEntity<Funcionario> response = restTemplate.exchange("/funcionarios/1", HttpMethod.PUT, entity, Funcionario.class);
        assertNotNull(response.getBody());
        assertEquals("João Silva", response.getBody().getNome());
    }

    @Test
    @DisplayName("Deve deletar um funcionário pelo ID")
    void testDeleteFuncionario() {
        doNothing().when(funcionarioService).delete(1L);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<Void> response = restTemplate.exchange("/funcionarios/1", HttpMethod.DELETE, entity, Void.class);
        assertEquals(204, response.getStatusCodeValue());
    }
}
