package dev.java10x.cadastroDeNinjas.missoes.repository;

import dev.java10x.cadastroDeNinjas.missoes.model.MissoesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissoesRepository extends JpaRepository<MissoesModel, Long> {
}
