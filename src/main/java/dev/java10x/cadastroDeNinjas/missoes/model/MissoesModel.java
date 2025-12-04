package dev.java10x.cadastroDeNinjas.missoes.model;

import dev.java10x.cadastroDeNinjas.ninjas.model.NinjaModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String dificuldade;

    @OneToMany(mappedBy = "missoes") // UMA missao podem ter MUITOS ninjas
    private List<NinjaModel> ninjas;
    
}
