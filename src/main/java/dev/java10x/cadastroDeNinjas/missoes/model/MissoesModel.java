package dev.java10x.cadastroDeNinjas.missoes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.java10x.cadastroDeNinjas.ninjas.model.NinjaModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

// Transforma classe em entidade
@Entity
@Table(name = "tb_missoes")
// Lombok
@NoArgsConstructor

@Data // Getters e Setters
@ToString
public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String dificuldade;

    @OneToMany(mappedBy = "missoes") // UMA missao podem ter MUITOS
    @JsonIgnore
    private List<NinjaModel> ninjas;

    public MissoesModel(Long id, String nome, String dificuldade, List<NinjaModel> ninjas) {
        this.id = id;
        this.nome = nome;
        this.dificuldade = dificuldade;
        this.ninjas = ninjas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public List<NinjaModel> getNinjas() {
        return ninjas;
    }

    public void setNinjas(List<NinjaModel> ninjas) {
        this.ninjas = ninjas;
    }
}
