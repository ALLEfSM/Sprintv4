package br.com.fiap.insights.controller.cliente;

import br.com.fiap.insights.model.Cliente;
import br.com.fiap.insights.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Cliente cliente, Model model) {
        return "cliente/cadastrar";
    }

    @PostMapping("cadastrar")
    @Transactional
    public String cadastar(@Valid Cliente cliente,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "cliente/cadastrar";
        }
        clienteRepository.save(cliente);
        redirectAttributes.addFlashAttribute
                ("mensagem", "cliente registrado com sucesso!");
        return "redirect:/cliente/cadastrar";
    }

    @GetMapping("listar")
    public String listclientes(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "cliente/listar";
    }

    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("cliente", clienteRepository.findById(id));
        return "cliente/editar";
    }

    @PostMapping("editar")
    public String editar(@Valid Cliente cliente,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "cliente/editar";
        }
        clienteRepository.save(cliente);
        redirectAttributes.addFlashAttribute("mensagem", "sucesso ao atualizar o cliente");
        return "redirect:/cliente/listar";
    }
    @PostMapping("excluir")
    @Transactional
    public String excluir(Long id, RedirectAttributes redirectAttributes) {
        clienteRepository.deleteById(id);
        redirectAttributes.addFlashAttribute
                ("mensagem", "Cliente removido da base de dados");
        return "redirect:/cliente/listar";
    }

    //@PostMapping("pesquisa")
  //  public String pesquisar(@RequestParam("query") String query, Model model) {
  //      List<Cliente> resultados = clienteRepository.findByNome(query);
  //      model.addAttribute("resultados", resultados);
  //      model.addAttribute("query", query);
  //      return "cliente/resultadosPesquisa";
  //  }



}
