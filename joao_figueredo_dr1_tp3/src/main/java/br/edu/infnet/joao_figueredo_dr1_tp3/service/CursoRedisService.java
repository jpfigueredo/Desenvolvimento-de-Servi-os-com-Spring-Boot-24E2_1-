package br.edu.infnet.joao_figueredo_dr1_tp3.service;

import br.edu.infnet.joao_figueredo_dr1_tp3.entity.Curso;
import br.edu.infnet.joao_figueredo_dr1_tp3.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CursoRedisService {

    private static final String CURSO_KEY = "CURSO";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private CursoRepository cursoRepository;

    public void cacheCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        redisTemplate.opsForValue().set(CURSO_KEY, cursos, 10, TimeUnit.MINUTES);
    }

    @SuppressWarnings("unchecked")
    public List<Curso> getCachedCursos() {
        return (List<Curso>) redisTemplate.opsForValue().get(CURSO_KEY);
    }

    public void evictCursosCache() {
        redisTemplate.delete(CURSO_KEY);
    }
}
