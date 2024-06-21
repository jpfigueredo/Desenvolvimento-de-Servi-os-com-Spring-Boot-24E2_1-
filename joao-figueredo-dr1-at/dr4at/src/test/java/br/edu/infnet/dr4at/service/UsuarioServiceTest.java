package br.edu.infnet.dr4at.service;

import br.edu.infnet.dr4at.model.Usuario;
import br.edu.infnet.dr4at.repository.UsuarioRepository;
import br.edu.infnet.dr4at.service.impl.UsuarioServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private Usuario usuario1;
    private Usuario usuario2;

    @BeforeEach
    void setUp() {
        usuario1 = new Usuario("1", "test", "test@test.test", "senha1", "ROLE_USER");
        usuario2 = new Usuario("2", "testtest", "testtest@test.test", "senha2", "ROLE_ADMIN");
    }

    @Test
    @DisplayName("Deve retornar todos os usuários")
    void testGetAll() {
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));
        List<Usuario> usuarios = usuarioService.getAll();
        assertNotNull(usuarios);
        assertEquals(2, usuarios.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar usuário por ID")
    void testGetById() {
        when(usuarioRepository.findById("1")).thenReturn(Optional.of(usuario1));
        Usuario usuario = usuarioService.getById("1");
        assertNotNull(usuario);
        assertEquals("test", usuario.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção quando usuário não é encontrado pelo ID")
    void testGetByIdNotFound() {
        when(usuarioRepository.findById("1")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> usuarioService.getById("1"));
    }

    @Test
    @DisplayName("Deve salvar um novo usuário")
    void testSave() {
        when(usuarioRepository.findUsuarioByEmail(usuario1.getEmail())).thenReturn(null);
        when(passwordEncoder.encode(usuario1.getSenha())).thenReturn("encodedSenha1");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario1);

        Usuario savedUsuario = usuarioService.save(usuario1);
        assertNotNull(savedUsuario);
        assertEquals("test", savedUsuario.getNome());

        verify(passwordEncoder, times(1)).encode("senha1");
    }


    @Test
    @DisplayName("Deve lançar exceção quando email já está em uso")
    void testSaveEmailInUse() {
        when(usuarioRepository.findUsuarioByEmail(usuario1.getEmail())).thenReturn(usuario1);
        assertThrows(IllegalArgumentException.class, () -> usuarioService.save(usuario1));
    }

    @Test
    @DisplayName("Deve atualizar um usuário existente")
    void testUpdate() {
        when(usuarioRepository.existsById("1")).thenReturn(true);
        when(passwordEncoder.encode(usuario1.getSenha())).thenReturn("encodedSenha1");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario1);

        Usuario updatedUsuario = usuarioService.update("1", usuario1);
        assertNotNull(updatedUsuario);
        assertEquals("test", updatedUsuario.getNome());
        verify(passwordEncoder, times(1)).encode("senha1");
    }

    @Test
    @DisplayName("Deve lançar exceção quando tentar atualizar usuário inexistente")
    void testUpdateNotFound() {
        when(usuarioRepository.existsById("1")).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> usuarioService.update("1", usuario1));
    }

    @Test
    @DisplayName("Deve deletar um usuário pelo ID")
    void testDelete() {
        when(usuarioRepository.existsById("1")).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById("1");

        usuarioService.delete("1");
        verify(usuarioRepository, times(1)).deleteById("1");
    }

    @Test
    @DisplayName("Deve lançar exceção quando tentar deletar usuário inexistente")
    void testDeleteNotFound() {
        when(usuarioRepository.existsById("1")).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> usuarioService.delete("1"));
    }
}
