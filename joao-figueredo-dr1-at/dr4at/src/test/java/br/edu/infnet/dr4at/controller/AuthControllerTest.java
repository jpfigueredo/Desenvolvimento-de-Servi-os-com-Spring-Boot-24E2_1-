package br.edu.infnet.dr4at.controller;

import br.edu.infnet.dr4at.model.Usuario;
import br.edu.infnet.dr4at.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@WithMockUser(username = "user", password = "password", roles = "ADMIN")
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve retornar todos os usuários")
    public void testGetAllUsuarios() throws Exception {
        Usuario usuario1 = new Usuario("1", "test", "test@test.com", "senha1", "ROLE_USER");
        Usuario usuario2 = new Usuario("2", "test2", "test2@test.com", "senha2", "ROLE_ADMIN");

        given(usuarioService.getAll()).willReturn(Arrays.asList(usuario1, usuario2));

        mockMvc.perform(get("/api/auth"))
                .andExpect(status().isCreated()) // Change to isOk()
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nome").value(usuario1.getNome()))
                .andExpect(jsonPath("$[1].nome").value(usuario2.getNome()));
    }


    @Test
    @DisplayName("Deve retornar usuário por ID")
    public void testGetUsuarioById() throws Exception {
        Usuario usuario = new Usuario("1", "test", "test@test.com", "senha1", "ROLE_USER");

        given(usuarioService.getById(anyString())).willReturn(usuario);

        mockMvc.perform(get("/api/auth/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value(usuario.getNome()));
    }

    @Test
    @DisplayName("Deve salvar um novo usuário")
    public void testSaveUsuario() throws Exception {
        Usuario usuario = new Usuario("1", "test", "test@test.com", "senha1", "ROLE_USER");

        given(usuarioService.save(any(Usuario.class))).willReturn(usuario);

        mockMvc.perform(post("/api/auth")
                        .with(csrf().asHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value(usuario.getNome()));
    }

    @Test
    @DisplayName("Deve atualizar um usuário existente")
    public void testUpdateUsuario() throws Exception {
        Usuario usuario = new Usuario("1", "test", "test@test.com", "senha1", "ROLE_USER");

        given(usuarioService.update(anyString(), any(Usuario.class))).willReturn(usuario);

        Usuario updatedUsuario = new Usuario("1", "testtest", "testtest@test.com", "senha2", "ROLE_ADMIN");

        mockMvc.perform(put("/api/auth/1")
                        .with(csrf().asHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUsuario)))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value(updatedUsuario.getNome()));
    }

    @Test
    @DisplayName("Deve deletar um usuário pelo ID")
    public void testDeleteUsuario() throws Exception {
        mockMvc.perform(delete("/api/auth/1")
                        .with(csrf().asHeader()))
                .andExpect(status().isAccepted());
    }

}
