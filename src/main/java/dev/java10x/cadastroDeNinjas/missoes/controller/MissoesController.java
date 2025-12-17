package dev.java10x.cadastroDeNinjas.missoes.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    // Adicionar Missao
    @PostMapping("/criar")
    public String criar(){
        return "Missao Criada";
    }

    // Mostrar todas as Missoes (READ)
    @GetMapping("/todos")
    public String mostrarTodasAsMissoes(){
        return "Missao lido";
    }


    // Mostrar Missao por ID (CREATE)
    @GetMapping("/MissoesID")
    public String mostrarMissaoPorId(){
        return "Missao por id";
    }

    // Alterar dados dos Missoes (UPDATE)
    @PutMapping("/alterarId")
    public String alterarMissoesPorId(){
        return "Missao Alterada ";
    }
    // Deletar Misseso (DELETE)
    @DeleteMapping("/deletarID")
    public String deletarMissoesPorId(){
        return "Missao deletado";
    }




}
