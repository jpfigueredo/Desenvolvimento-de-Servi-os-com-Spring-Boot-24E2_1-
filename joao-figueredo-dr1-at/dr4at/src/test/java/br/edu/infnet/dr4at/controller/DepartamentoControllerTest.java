package br.edu.infnet.dr4at.controller;

import br.edu.infnet.dr4at.model.Departamento;
import br.edu.infnet.dr4at.service.DepartamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartamentoController.class)
@WithMockUser(username = "user", password = "password", roles = "ADMIN")
public class DepartamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartamentoService departamentoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve retornar todos os departamentos")
    public void testGetAllDepartamentos() throws Exception {
        Departamento departamento1 = new Departamento(1L, "TI", "SAO PAULO");
        Departamento departamento2 = new Departamento(2L, "RH", "CURITIBA");

        given(departamentoService.getAll()).willReturn(List.of(departamento1, departamento2));

        mockMvc.perform(get("/api/departamentos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nome").value(departamento1.getNome()))
                .andExpect(jsonPath("$[1].nome").value(departamento2.getNome()));
    }

    @Test
    @DisplayName("Deve retornar um departamento pelo ID")
    public void testGetDepartamentoById() throws Exception {
        Departamento departamento = new Departamento(1L, "TI", "SAO PAULO");

        given(departamentoService.getById(anyLong())).willReturn(departamento);

        mockMvc.perform(get("/api/departamentos/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value(departamento.getNome()));
    }

    @Test
    @DisplayName("Deve criar um novo departamento")
    public void testCreateDepartamento() throws Exception {
        Departamento departamento = new Departamento(1L, "TI", "SAO PAULO");

        given(departamentoService.save(any(Departamento.class))).willReturn(departamento);

        mockMvc.perform(post("/api/departamentos")
                        .with(csrf().asHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departamento)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value(departamento.getNome()));
    }

    @Test
    @DisplayName("Deve atualizar um departamento existente")
    public void testUpdateDepartamento() throws Exception {
        Departamento departamento = new Departamento(1L, "TI", "SAO PAULO");

        given(departamentoService.update(anyLong(), any(Departamento.class))).willReturn(departamento);

        mockMvc.perform(put("/api/departamentos/1")
                        .with(csrf().asHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departamento)))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value(departamento.getNome()));
    }

    @Test
    @DisplayName("Deve deletar um departamento pelo ID")
    public void testDeleteDepartamento() throws Exception {
        mockMvc.perform(delete("/api/departamentos/1")
                        .with(csrf().asHeader()))
                .andExpect(status().isAccepted());
    }
}
