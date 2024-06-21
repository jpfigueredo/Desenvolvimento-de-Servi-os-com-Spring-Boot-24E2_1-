package br.edu.infnet.dr4_at.controller;

import br.edu.infnet.dr4_at.model.Departamento;
import br.edu.infnet.dr4_at.service.DepartamentoService;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user", password = "password", roles = "ADMIN")
class DepartamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private DepartamentoService departamentoService;

    @InjectMocks
    private DepartamentoController departamentoController;

    private Departamento departamento1;
    private Departamento departamento2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departamento1 = new Departamento(1L, "Desenvolvimento", "São Paulo");
        departamento2 = new Departamento(2L, "Marketing", "Rio de Janeiro");
    }

    @Test
    @DisplayName("Deve retornar todos os departamentos")
    void testGetAllDepartamentos() {
        when(departamentoService.getAll()).thenReturn(List.of(departamento1, departamento2));
        ResponseEntity<Departamento[]> response = restTemplate.getForEntity("/departamentos", Departamento[].class);
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);
    }

    @Test
    @DisplayName("Deve retornar um departamento pelo ID")
    void testGetDepartamentoById() {
        when(departamentoService.getById(1L)).thenReturn(java.util.Optional.of(departamento1));
        ResponseEntity<Departamento> response = restTemplate.getForEntity("/departamentos/1", Departamento.class);
        assertNotNull(response.getBody());
        assertEquals("Desenvolvimento", response.getBody().getNome());
    }

    @Test
    @DisplayName("Deve criar um novo departamento")
    void testCreateDepartamento() {
        when(departamentoService.save(any(Departamento.class))).thenReturn(departamento1);
        ResponseEntity<Departamento> response = restTemplate.postForEntity("/departamentos", departamento1, Departamento.class);
        assertNotNull(response.getBody());
        assertEquals("Desenvolvimento", response.getBody().getNome());
    }

    @Test
    @DisplayName("Deve atualizar um departamento existente")
    void testUpdateDepartamento() {
        when(departamentoService.update(anyLong(), any(Departamento.class))).thenReturn(departamento1);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Departamento> entity = new HttpEntity<>(departamento1, headers);
        ResponseEntity<Departamento> response = restTemplate.exchange("/departamentos/1", HttpMethod.PUT, entity, Departamento.class);
        assertNotNull(response.getBody());
        assertEquals("Desenvolvimento", response.getBody().getNome());
    }

    @Test
    @DisplayName("Deve deletar um departamento pelo ID")
    void testDeleteDepartamento() {
        doNothing().when(departamentoService).delete(1L);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<Void> response = restTemplate.exchange("/departamentos/1", HttpMethod.DELETE, entity, Void.class);
        assertEquals(204, response.getStatusCodeValue());
    }
}
