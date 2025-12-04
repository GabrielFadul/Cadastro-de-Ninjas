package dev.java10x.cadastroDeNinjas.ninjas.repository;

import dev.java10x.cadastroDeNinjas.ninjas.model.NinjaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NinjaRepository extends JpaRepository<NinjaModel, Long> {
}
