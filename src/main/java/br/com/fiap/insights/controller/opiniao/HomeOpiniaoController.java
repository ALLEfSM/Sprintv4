package br.com.fiap.insights.controller.opiniao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeOpiniaoController {
    @GetMapping("/homeOpiniao")
    public String home() {
        return "/opiniao/opiniaohome";
    }
}
