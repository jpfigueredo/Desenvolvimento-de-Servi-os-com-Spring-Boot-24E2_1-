package br.edu.infnet.joao_figueredo_dr1_tp3.controller;

import br.edu.infnet.joao_figueredo_dr1_tp3.entity.MaterialDidatico;
import br.edu.infnet.joao_figueredo_dr1_tp3.service.MaterialDidaticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materiais")
public class MaterialDidaticoController {

    @Autowired
    private MaterialDidaticoService service;

    @GetMapping
    public List<MaterialDidatico> getAllMateriais() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDidatico> getMaterialById(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MaterialDidatico createMaterial(@RequestBody MaterialDidatico materialDidatico) {
        return service.save(materialDidatico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialDidatico> updateMaterial(@PathVariable String id, @RequestBody MaterialDidatico materialDetails) {
        return service.findById(id)
                .map(materialDidatico -> {
                    materialDidatico.setTitulo(materialDetails.getTitulo());
                    materialDidatico.setDescricao(materialDetails.getDescricao());
                    return ResponseEntity.ok(service.save(materialDidatico));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable String id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
