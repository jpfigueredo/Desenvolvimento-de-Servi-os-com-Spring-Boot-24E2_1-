package br.edu.infnet.dr4_at.controller;

import br.edu.infnet.dr4_at.model.Departamento;
import br.edu.infnet.dr4_at.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService service;

    @GetMapping
    public List<Departamento> getAllDepartamentos() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> getDepartamentoById(@PathVariable Long id) {
        Optional<Departamento> departamento = service.getById(id);
        return departamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Departamento createDepartamento(@RequestBody Departamento departamento) {
        return service.save(departamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> updateDepartamento(@PathVariable Long id, @RequestBody Departamento departamento) {
        Departamento updatedDepartamento = service.update(id, departamento);
        return updatedDepartamento != null ? ResponseEntity.ok(updatedDepartamento) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartamento(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
