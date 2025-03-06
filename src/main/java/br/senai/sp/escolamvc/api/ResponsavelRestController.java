package br.senai.sp.escolamvc.api;

import br.senai.sp.escolamvc.model.Responsavel;
import br.senai.sp.escolamvc.repository.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responsavel")
public class ResponsavelRestController {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @GetMapping("/listar")
    public List<Responsavel> listar() {
        return responsavelRepository.findAll();
    }

    @PostMapping("/inserir")
    public Responsavel inserir(@RequestBody Responsavel responsavel) {
        return responsavelRepository.save(responsavel);
    }

    @PutMapping("/alterar")
    public Responsavel alterar(@RequestBody Responsavel responsavel) {
        return responsavelRepository.save(responsavel);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable Long id) {
        responsavelRepository.deleteById(id);
    }

    @PostMapping("/inserir-varios")
    public void inserirVarios(@RequestBody List<Responsavel> responsavels) {
        responsavelRepository.saveAll(responsavels);
    }

    @GetMapping("/buscar/{id}")
    public Responsavel buscar(@PathVariable Long id) {
        return responsavelRepository.findById(id).get();
    }

    @GetMapping("/buscar-por-nome/{nome}")
    public List<Responsavel> buscarPorNome(@PathVariable String nome) {
        return responsavelRepository.findResponsavelsByNome(nome);
    }
}
