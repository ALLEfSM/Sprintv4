package br.com.fiap.insights.controller.opiniao;

import br.com.fiap.insights.model.Opiniao;
import br.com.fiap.insights.repository.OpiniaoRepository;
import br.com.fiap.insights.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("opiniao")
public class OpiniaoController {

    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("cadastrar")
    public String cadastrar(Opiniao opiniao, Model model){
        model.addAttribute("produtos", produtoRepository.findAll());
        return "opiniao/cadastrar";
    }

    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Opiniao opiniao,
                            BindingResult result,
                            RedirectAttributes redirectAttributes){
        if(result.hasErrors()) {
            return "opiniao/cadastrar";
        }
        opiniaoRepository.save(opiniao);
        redirectAttributes.addFlashAttribute
                ("mensagem", "Feedback registrado com sucesso!");
        return "redirect:/opiniao/cadastrar";
    }

    @GetMapping("listar")
    public String listarProdutos(Model model) {
        model.addAttribute("opinioes", opiniaoRepository.findAll());
        return "opiniao/listar";
    }

    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("opiniao", opiniaoRepository.findById(id));
        return "opiniao/editar";
    }

    @PostMapping("editar")
    public String editar(@Valid Opiniao opiniao, RedirectAttributes redirectAttributes,
                         BindingResult result){
        if(result.hasErrors()) {
            return "opiniao/editar";
        }
        opiniaoRepository.save(opiniao);
        redirectAttributes.addFlashAttribute("mensagem", " atualizado com sucesso!");
        return "redirect:/opiniao/listar";
    }
    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        opiniaoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Comentário removido");
        return "redirect:/opiniao/listar";
    }



}
