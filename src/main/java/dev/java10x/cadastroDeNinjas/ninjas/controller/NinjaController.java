package dev.java10x.cadastroDeNinjas.ninjas.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa eh a minha primeira mensagem";
    }

    // Adicionar Ninja (CREATE)
    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja Criado";
    }

    // Mostrar todos os Ninjas (READ)
    @GetMapping("/todos")
    public String mostrarTodosOsNinjas(){
        return "Ninja lido";
    }


    // Mostrar Ninja por ID (CREATE)
    @GetMapping("/todosID")
    public String mostrarTodosOsNinjasPorId(){
        return "Ninja lido id";
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterarId")
    public String alterarNinjasPorId(){
        return "Alterar Ninja ";
    }
    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletarID")
    public String deletarNinjasPorId(){
        return "Ninja deletado";
    }
}
