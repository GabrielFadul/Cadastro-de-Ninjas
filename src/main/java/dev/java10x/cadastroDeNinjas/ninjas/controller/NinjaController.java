package dev.java10x.cadastroDeNinjas.ninjas.controller;

import dev.java10x.cadastroDeNinjas.ninjas.model.NinjaModel;
import dev.java10x.cadastroDeNinjas.ninjas.service.NinjaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class NinjaController {
        private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa eh a minha primeira mensagem";
    }

    // Adicionar Ninja (CREATE)
    @PostMapping("/criar")
    public NinjaModel criarNinja(@RequestBody NinjaModel ninjaModel){
        return ninjaService.criarNinja(ninjaModel);
    }

    // Mostrar todos os Ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas(){
        return ninjaService.listarNinjas(); // retorna o metodo do service que lista
    }


    // Mostrar Ninja por ID (CREATE)
    @GetMapping("/listar/{id}")
    public NinjaModel mostrarTodosOsNinjasPorId(@PathVariable Long id){
        return ninjaService.listarNinjaPorId(id);
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterar/{id}")
    public void String alterarNinjasPorId(@PathVariable Long id){
        
    }
    // Deletar Ninja (DELETE) - Sempre void
    @DeleteMapping("/deletar/{id}")
    public void deletarNinjasPorId(@PathVariable Long id){
        ninjaService.deletar(id); ;
    }
}
