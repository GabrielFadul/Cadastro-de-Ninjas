package dev.java10x.cadastroDeNinjas.ninjas.controller;

import dev.java10x.cadastroDeNinjas.ninjas.dtos.NinjaDTO;
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
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninjaDTO){
        return ninjaService.criarNinja(ninjaDTO);
    }

    // Mostrar todos os Ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaDTO> listarNinjas(){
        return ninjaService.listarNinjas(); // retorna o metodo do service que lista
    }


    // Mostrar Ninja por ID (CREATE)
    @GetMapping("/listar/{id}")
    public NinjaDTO mostrarTodosOsNinjasPorId(@PathVariable Long id){
        return ninjaService.listarNinjaPorId(id);
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/atualizar/{id}")
    public NinjaDTO alterarNinjasPorId(@PathVariable Long id,
                                         @RequestBody NinjaDTO ninjaAtualizado){

        return ninjaService.atualizarNinja(id, ninjaAtualizado);
    }
    // Deletar Ninja (DELETE) - Sempre void
    @DeleteMapping("/deletar/{id}")
    public void deletarNinjasPorId(@PathVariable Long id){
        ninjaService.deletar(id); ;
    }
}
