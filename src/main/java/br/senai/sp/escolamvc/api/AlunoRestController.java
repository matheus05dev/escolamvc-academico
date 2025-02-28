package br.senai.sp.escolamvc.api;

import br.senai.sp.escolamvc.model.Aluno;
import br.senai.sp.escolamvc.repository.AlunoRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aluno")
public class AlunoRestController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("/listar")
    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    @PostMapping("/inserir")
    public Aluno inserir(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @PutMapping("/alterar")
    public Aluno alterar(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable Long id) {
        alunoRepository.deleteById(id);
    }

    @PostMapping("/inserir-varios")
    public void inserirVarios(@RequestBody List<Aluno> alunos) {
        alunoRepository.saveAll(alunos);
    }

    @GetMapping("/buscar/{id}")
    public Aluno buscar(@PathVariable Long id) {
        return alunoRepository.findById(id).get();
    }

    @GetMapping("/buscar-por-nome/{nome}")
    public List<Aluno> buscarPorNome(@PathVariable String nome) {
        return alunoRepository.findAlunosByNomeContaining(nome);
    }
}
