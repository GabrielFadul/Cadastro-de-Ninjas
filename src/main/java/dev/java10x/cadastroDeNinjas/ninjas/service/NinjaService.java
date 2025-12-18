package dev.java10x.cadastroDeNinjas.ninjas.service;

import dev.java10x.cadastroDeNinjas.ninjas.dtos.NinjaDTO;
import dev.java10x.cadastroDeNinjas.ninjas.mapper.NinjaMapper;
import dev.java10x.cadastroDeNinjas.ninjas.model.NinjaModel;
import dev.java10x.cadastroDeNinjas.ninjas.repository.NinjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {


    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
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
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO){ // Retornar um DTO e precisa vir um DTO de parametro (vem do controller)
        NinjaModel ninjaModel = ninjaMapper.map(ninjaDTO); // Mapeia esse DTO e converte em Model
        ninjaModel = ninjaRepository.save(ninjaModel); // Pego esse model, e salvo
        return ninjaMapper.map(ninjaModel); // retorno o model novo mas convertido de DTO
    }

    // Deletar um ninja - tem que ser VOID
    public void deletar(Long id){
        ninjaRepository.deleteById(id); ;
    }

    // Atualizar Ninja
    public NinjaModel atualizarNinja(Long id, NinjaModel ninjaAtualizado) {
        if (ninjaRepository.existsById(id)) { // Verifica se o id existe
            ninjaAtualizado.setId(id); // seta o mesmo ID na nova entidade
            return ninjaRepository.save(ninjaAtualizado); // salva nova entidade atualizada
        }
        return null;
    }

}
