package dev.java10x.cadastroDeNinjas.missoes.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.java10x.cadastroDeNinjas.ninjas.model.NinjaModel;
import jakarta.persistence.OneToMany;

import java.util.List;


public class MissoesDTO {

    private Long id;
    private String nome;
    private String dificuldade;
    private List<NinjaModel> ninjas;

    public MissoesDTO(){
    }

    public MissoesDTO(Long id, String nome, String dificuldade, List<NinjaModel> ninjas) {
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
