package dev.java10x.cadastroDeNinjas.ninjas.model;

// Java Persistence API
import dev.java10x.cadastroDeNinjas.missoes.model.MissoesModel;
import jakarta.persistence.*;
import lombok.*;


// Transforma classe em entidade
@Entity
@Table(name = "tb_cadastro") // da o nome a tabela
// lombok
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(unique = true) // Coluna UNICA, nao tera repetido
    private String email;

    @Column(name = "idade")
    private int idade;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne // MUITOS ninjas podem ter UMA missao
    @JoinColumn(name = "missoes_id") // FK na tabela ninja → referência à missão
    private MissoesModel missoes;

    @Column(name = "rank")
    private String rank;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public MissoesModel getMissoes() {
        return missoes;
    }

    public void setMissoes(MissoesModel missoes) {
        this.missoes = missoes;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
