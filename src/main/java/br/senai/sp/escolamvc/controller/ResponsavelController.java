package br.senai.sp.escolamvc.controller;

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
    ResponsavelRepository responsavelRepository;

    @PostMapping("/buscar")
    public String buscar(@Param("nome") String nome, Model model){
        if(nome == null){
            model.addAttribute("responsaveis", responsavelRepository.findAll());
            return "responsavel/listagem";
        }

        List<Responsavel> responsaveisBuscadosNoBanco =
                responsavelRepository.findResponsavelsByNomeContaining(nome);
        model.addAttribute("responsaveis", responsaveisBuscadosNoBanco);

        return "responsavel/listagem";
    }



    /*
     * Método que direciona para templates/responsaveis/listagem.html
     */
    @GetMapping
    public String listagem(Model model) {

        // Busca a lista de responsaveis no banco de dados
        List<Responsavel> listaResponsaveis = responsavelRepository.findAll();

        // Adiciona a lista de responsaveis no objeto model para ser carregado no template
        model.addAttribute("responsaveis", listaResponsaveis);

        // Retorna o template responsavel/listagem.html
        return "responsavel/listagem";
    }

    /*
     * Método de acesso à página http://localhost:8080/responsavel/novo
     */
    @GetMapping("/form-inserir")
    public String formInserir(Model model){

        model.addAttribute("responsavel", new Responsavel());
        // templates/responsavel/inserir.html
        return "responsavel/inserir";
    }

    @PostMapping("/salvar")
    public String salvarResponsavel(
            @Valid Responsavel responsavel,
            BindingResult result,
            RedirectAttributes attributes) {

        // Se houver erro de validação, retorna para o template responsaveis/inserir.html
        if (result.hasErrors()){

            if(responsavel.getId() != null){
                return "responsavel/alterar";
            }

            return "responsavel/inserir";
        }

        // Salva o responsavel no banco de dados
        responsavelRepository.save(responsavel);

        // Adiciona uma mensagem que será exibida no template
        attributes.addFlashAttribute("mensagem", "Responsavel salvo com sucesso!");

        // Redireciona para a página de listagem de responsaveis
        return "redirect:/responsavel";
    }



    /*
     * Método para excluir um responsavel
     */
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id,
                          RedirectAttributes attributes) {

        // Busca o responsavel no banco de dados
        Responsavel responsavel = responsavelRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("ID inválido"));

        // Exclui o responsavel do banco de dados
        responsavelRepository.delete(responsavel);

        // Adiciona uma mensagem que será exibida no template
        attributes.addFlashAttribute("mensagem",
                "Responsavel excluído com sucesso!");

        // Redireciona para a página de listagem de responsaveis
        return "redirect:/responsavel";
    }



    /*
     * Método que direciona para templates/responsaveis/alterar.html
     */
    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model) {

        // Busca o responsavel no banco de dados
        Responsavel responsavel = responsavelRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("ID inválido"));

        // Adiciona o responsavel no objeto model para ser carregado no formulário
        model.addAttribute("responsavel", responsavel);

        // Retorna o template responsavel/alterar.html
        return "responsavel/alterar";
    }



}
