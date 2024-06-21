package br.edu.infnet.dr4at.controller;

import br.edu.infnet.dr4at.model.Funcionario;
import br.edu.infnet.dr4at.service.FuncionarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FuncionarioController.class)
@WithMockUser(username = "user", password = "password", roles = "ADMIN")
class FuncionarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuncionarioService funcionarioService;

    @Autowired
    private ObjectMapper objectMapper;

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
    void testGetAllFuncionarios() throws Exception {
        given(funcionarioService.getAll()).willReturn(List.of(funcionario1, funcionario2));

        mockMvc.perform(get("/api/funcionarios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nome").value(funcionario1.getNome()))
                .andExpect(jsonPath("$[1].nome").value(funcionario2.getNome()));
    }

    @Test
    @DisplayName("Deve retornar um funcionário pelo ID")
    void testGetFuncionarioById() throws Exception {
        given(funcionarioService.getById(anyLong())).willReturn(funcionario1);

        mockMvc.perform(get("/api/funcionarios/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value(funcionario1.getNome()));
    }

    @Test
    @DisplayName("Deve criar um novo funcionário")
    void testCreateFuncionario() throws Exception {
        given(funcionarioService.save(any(Funcionario.class))).willReturn(funcionario1);

        mockMvc.perform(post("/api/funcionarios")
                        .with(csrf().asHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionario1)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value(funcionario1.getNome()));
    }

    @Test
    @DisplayName("Deve atualizar um funcionário existente")
    void testUpdateFuncionario() throws Exception {
        given(funcionarioService.update(anyLong(), any(Funcionario.class))).willReturn(funcionario1);

        mockMvc.perform(put("/api/funcionarios/1")
                        .with(csrf().asHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionario1)))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value(funcionario1.getNome()));
    }

    @Test
    @DisplayName("Deve deletar um funcionário pelo ID")
    void testDeleteFuncionario() throws Exception {
        doNothing().when(funcionarioService).delete(anyLong());

        mockMvc.perform(delete("/api/funcionarios/1")
                        .with(csrf().asHeader()))
                .andExpect(status().isAccepted());
    }
}
