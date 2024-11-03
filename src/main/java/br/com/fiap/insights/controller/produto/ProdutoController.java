package br.com.fiap.insights.controller.produto;

import br.com.fiap.insights.model.Produto;
import br.com.fiap.insights.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("cadastrar")
    public String cadastrar(Produto produto, Model model){
        return "produto/cadastrar";
    }

    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Produto produto, BindingResult result,
                            RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "produto/cadastrar";
        }
        produtoRepository.save(produto);
        redirectAttributes.addFlashAttribute
                ("mensagem", "Produto cadastrado!");
        return "redirect:/produto/cadastrar";
    }

    @GetMapping("listar")
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "produto/listar";
    }

    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("produto", produtoRepository.findById(id));
        return "produto/editar";
    }

    @PostMapping("editar")
    public String editar(@Valid Produto produto,
                         BindingResult result,
                         RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "produto/editar";
        }
        produtoRepository.save(produto);
        redirectAttributes.addFlashAttribute("mensagem", "Produto atualizado");
        return "redirect:/produto/listar";
    }
    @PostMapping("excluir")
    @Transactional
    public String excluir(Long id, RedirectAttributes redirectAttributes) {
        produtoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Produto removido");
        return "redirect:/produto/listar";
    }

}
