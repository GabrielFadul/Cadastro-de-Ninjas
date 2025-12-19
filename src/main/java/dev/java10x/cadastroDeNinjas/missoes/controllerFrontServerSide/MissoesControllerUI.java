package dev.java10x.cadastroDeNinjas.missoes.controllerFrontServerSide;

import dev.java10x.cadastroDeNinjas.missoes.dtos.MissoesDTO;
import dev.java10x.cadastroDeNinjas.missoes.service.MissoesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControllerUI {

    private final MissoesService missoesService;

    public MissoesControllerUI(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    public String mostrarTodasAsMissoes(Model model){
        List<MissoesDTO> lista = missoesService.listar(); // chama o service e guarda o retorno em uma lista
        model.addAttribute("missoes", lista); // Pego a lista e coloco como atributo para rederizar com nome de missoes
        return "listarMissoes"; // Retorno o nome do arquivo da pagina que sera renderizada
    }

    @GetMapping("/deletar/{id}")
    public String deletarMissao(@PathVariable Long id){
        missoesService.delete(id);
        return "redirect:/missoes/ui/listar";
    }
}
