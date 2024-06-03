package br.edu.infnet.joao_figueredo_dr1_tp3.controller;

import br.edu.infnet.joao_figueredo_dr1_tp3.entity.Curso;
import br.edu.infnet.joao_figueredo_dr1_tp3.service.CursoRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/redis/cursos")
public class CursoRedisController {

    @Autowired
    private CursoRedisService cursoRedisService;

    @PostMapping("/cache")
    public void cacheCursos() {
        cursoRedisService.cacheCursos();
    }

    @GetMapping
    public List<Curso> getCachedCursos() {
        return cursoRedisService.getCachedCursos();
    }

    @PostMapping("/evict")
    public void evictCursosCache() {
        cursoRedisService.evictCursosCache();
    }
}
