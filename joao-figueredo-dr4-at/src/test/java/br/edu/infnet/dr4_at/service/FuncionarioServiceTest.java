package br.edu.infnet.dr4_at.service;

import br.edu.infnet.dr4_at.model.Funcionario;
import br.edu.infnet.dr4_at.repository.FuncionarioRepository;
import br.edu.infnet.dr4_at.service.impl.FuncionarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioServiceImpl funcionarioService;

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
        when(funcionarioRepository.findAll()).thenReturn(Arrays.asList(funcionario1, funcionario2));
        List<Funcionario> funcionarioList = funcionarioService.getAll();
        assertNotNull(funcionarioList);
        assertFalse(funcionarioList.isEmpty());
        assertEquals(2, funcionarioList.size());
    }

    @Test
    @DisplayName("Deve retornar um funcionário pelo ID")
    void testGetFuncionarioById() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario1));
        Optional<Funcionario> funcionario = funcionarioService.getById(1L);
        assertTrue(funcionario.isPresent());
        assertEquals("João Silva", funcionario.get().getNome());
    }

    @Test
    @DisplayName("Deve salvar um novo funcionário")
    void testSaveFuncionario() {
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario1);
        Funcionario savedFuncionario = funcionarioService.save(funcionario1);
        assertNotNull(savedFuncionario);
        assertEquals("João Silva", savedFuncionario.getNome());
    }

    @Test
    @DisplayName("Deve atualizar um funcionário existente")
    void testUpdateFuncionario() {
        when(funcionarioRepository.existsById(1L)).thenReturn(true);
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario1);
        Funcionario updatedFuncionario = funcionarioService.update(1L, funcionario1);
        assertNotNull(updatedFuncionario);
        assertEquals("João Silva", updatedFuncionario.getNome());
    }

    @Test
    @DisplayName("Deve deletar um funcionário pelo ID")
    void testDeleteFuncionario() {
        doNothing().when(funcionarioRepository).deleteById(1L);
        funcionarioService.delete(1L);
        verify(funcionarioRepository, times(1)).deleteById(1L);
    }
}
