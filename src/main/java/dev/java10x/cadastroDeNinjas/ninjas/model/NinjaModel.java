package dev.java10x.cadastroDeNinjas.ninjas.model;

// Java Persistence API
import dev.java10x.cadastroDeNinjas.missoes.model.MissoesModel;
import jakarta.persistence.*;


// Transforma classe em entidade
@Entity
@Table(name = "tb_cadastro") // da o nome a tabela
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private int idade;

    @ManyToOne // MUITOS ninjas podem ter UMA missao
    @JoinColumn(name = "missoes_id") // FK na tabela ninja → referência à missão
    private MissoesModel missoes;


    public NinjaModel() {
    }

    public NinjaModel(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    public NinjaModel(String nome, String email, int idade, MissoesModel missoes){
        this(nome, email, idade);
        this.missoes = missoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
