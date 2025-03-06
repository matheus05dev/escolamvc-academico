package br.senai.sp.escolamvc.controller;

import br.senai.sp.escolamvc.model.Aluno;
import br.senai.sp.escolamvc.model.Responsavel;
import br.senai.sp.escolamvc.repository.ResponsavelRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/responsavel")
public class ResponsavelController {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @PostMapping("/buscar")
    public String buscar(@Param("nome") String nome, Model model) {
        if (nome == null) {
            model.addAttribute("responsaveis", responsavelRepository.findAll());
            return "responsavel/listagem";
        }
        List<Responsavel> responsaveisBuscadosNoBanco = responsavelRepository.findResponsavelsByNomeContaining(nome);
        model.addAttribute("responsaveis", responsaveisBuscadosNoBanco);
        return "responsavel/listagem";
    }



    @GetMapping("/form-inserir")
    public String formInserir(Model model) {
        Responsavel Responsavel = new Responsavel();
        model.addAttribute("responsavel", new Responsavel());

        return "responsavel/inserir";

    }

    @PostMapping("/salvar")
    public String salvarResponsavel(@Valid Responsavel Responsavel, BindingResult result,
                              RedirectAttributes attributes) {


        if (result.hasErrors()) {
            return "responsavel/inserir";
        }

        // Salva o aluno no banco de dados
        responsavelRepository.save(Responsavel);

        // Adiciona uma mensagem que será exibida no template
        attributes.addFlashAttribute("mensagem", "Responsavel salvo com sucesso!");

        // Redireciona para a página de listagem de alunos
        return "redirect:/responsavel/form-inserir";
    }


    @GetMapping
    public String listagem(Model model) {

        // Busca a lista de alunos no banco de dados
        List<Responsavel> listaResponsavel = responsavelRepository.findAll();

        // Adiciona a lista de alunos no objeto model para ser carregado no template
        model.addAttribute("responsaveis", listaResponsavel);

        // Retorna o template aluno/listagem.html
        return "responsavel/listagem";
    }

    /*
     * Método para excluir um aluno
     */
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id,
                          RedirectAttributes attributes) {

        // Busca o aluno no banco de dados
        Responsavel Responsavel = responsavelRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("ID inválido"));

        // Exclui o aluno do banco de dados
        responsavelRepository.delete(Responsavel);

        // Adiciona uma mensagem que será exibida no template
        attributes.addFlashAttribute("mensagem",
                "Responsavel excluído com sucesso!");

        // Redireciona para a página de listagem de alunos
        return "redirect:/responsavel";
    }
    /*
     * Método que direciona para templates/alunos/alterar.html
     */
    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model) {

        // Busca o aluno no banco de dados
        Responsavel Responsavel = responsavelRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("ID inválido"));

        // Adiciona o aluno no objeto model para ser carregado no formulário
        model.addAttribute("responsavel", Responsavel);

        // Retorna o template aluno/alterar.html
        return "responsavel/alterar";
    }
    @PostMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, @Valid Responsavel Responsavel,
                          BindingResult result, RedirectAttributes attributes) {

        // Se houver erro de validação, retorna para o template alunos/alterar.html
        if (result.hasErrors()) {
            return "responsavel/alterar";
        }

        // Busca o aluno no banco de dados
        Responsavel ResponsavelAtualizado = responsavelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido"));


        // Seta os dados do aluno
        ResponsavelAtualizado.setNome(Responsavel.getNome());
        ResponsavelAtualizado.setEmail(Responsavel.getEmail());
        ResponsavelAtualizado.setCpf(Responsavel.getCpf());

        // Salva o aluno no banco de dados
        responsavelRepository.save(ResponsavelAtualizado);

        // Adiciona uma mensagem que será exibida no template
        attributes.addFlashAttribute("mensagem",
                "Responsavel atualizado com sucesso!");

        // Redireciona para a página de listagem de alunos
        return "redirect:/responsavel";
    }
}
