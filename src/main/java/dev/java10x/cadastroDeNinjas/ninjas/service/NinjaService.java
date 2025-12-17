package dev.java10x.cadastroDeNinjas.ninjas.service;

import dev.java10x.cadastroDeNinjas.ninjas.model.NinjaModel;
import dev.java10x.cadastroDeNinjas.ninjas.repository.NinjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {


    private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }


    // Listar todos os meus ninjas
    public List<NinjaModel> listarNinjas(){
        return ninjaRepository.findAll();
    }

    // Listar Ninja por id
    public NinjaModel listarNinjaPorId(Long id){
        // Optional eh o que pode estar ou nao, para evitar nullPointerException
        Optional<NinjaModel> ninjaModelporId = ninjaRepository.findById(id);
        return ninjaModelporId.orElse(null); // retorna o ninjaModel, se nao houver retorna null
    }

    // Criar um novo ninja
    public NinjaModel criarNinja(NinjaModel ninjaModel){
        return ninjaRepository.save(ninjaModel);
    }

    // Deletar um ninja - tem que ser VOID
    public void deletar(Long id){
        ninjaRepository.deleteById(id); ;
    }

}
