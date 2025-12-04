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
}
