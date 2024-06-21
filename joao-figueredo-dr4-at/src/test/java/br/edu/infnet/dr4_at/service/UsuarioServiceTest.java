package br.edu.infnet.dr4_at.service;

import br.edu.infnet.dr4_at.model.Usuario;
import br.edu.infnet.dr4_at.repository.UsuarioRepository;
import br.edu.infnet.dr4_at.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar todos os usuários")
    void testGetAll() {
        Usuario usuario1 = new Usuario("1", "test", "test@test.com", "senha1", "USER");
        Usuario usuario2 = new Usuario("2", "test2", "test2@test.com", "senha2", "ADMIN");

        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        List<Usuario> usuarios = usuarioService.getAll();

        assertNotNull(usuarios);
        assertEquals(2, usuarios.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar usuário por ID")
    void testGetById() {
        Usuario usuario = new Usuario("João", "senha1", "USER");
        when(usuarioRepository.findById("1")).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.getById("1").get();

        assertNotNull(result);
        assertEquals("João", result.getNome());
        verify(usuarioRepository, times(1)).findById("1");
    }

    @Test
    @DisplayName("Deve salvar um novo usuário")
    void testSave() {
        Usuario usuario = new Usuario("João", "senha1", "USER");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario result = usuarioService.save(usuario);

        assertNotNull(result);
        assertEquals("João", result.getNome());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    @DisplayName("Deve atualizar um usuário existente")
    void testUpdate() {
        Usuario usuario = new Usuario("João", "senha1", "USER");

        when(usuarioRepository.findById("1")).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario updatedUsuario = new Usuario("João Atualizado", "senha2", "ADMIN");
        Usuario result = usuarioService.update("1", updatedUsuario);

        assertEquals("João Atualizado", result.getNome());
        assertEquals("senha2", result.getSenha());
        assertEquals("ADMIN", result.getPapel());
        verify(usuarioRepository, times(1)).findById("1");
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    @DisplayName("Deve deletar um usuário pelo ID")
    void testDeleteUsuario() {
        doNothing().when(usuarioRepository).deleteById("1");
        usuarioService.delete("1");
        verify(usuarioRepository, times(1)).deleteById("1");
    }
}
