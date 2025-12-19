package dev.java10x.cadastroDeNinjas.ninjas.service;

import dev.java10x.cadastroDeNinjas.ninjas.dtos.NinjaDTO;
import dev.java10x.cadastroDeNinjas.ninjas.mapper.NinjaMapper;
import dev.java10x.cadastroDeNinjas.ninjas.model.NinjaModel;
import dev.java10x.cadastroDeNinjas.ninjas.repository.NinjaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {


    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }


    // Listar todos os meus ninjas
    public List<NinjaDTO> listarNinjas(){
         List<NinjaModel> ninjas = ninjaRepository.findAll();  // Recebe os models do FindAll() para ser percorrido e transformado em DTO
         return ninjas.stream() // Transforma em stream
                 .map(ninjaMapper::map) //mapaia a classe Mapper e usa o metodo
                 .collect(Collectors.toList()); // Acumula os mapeamentos em uma lista
    }

    // Listar Ninja por id
    public NinjaDTO listarNinjaPorId(Long id){
        // Optional eh o que pode estar ou nao, para evitar nullPointerException
        Optional<NinjaModel> ninjaModelporId = ninjaRepository.findById(id);
        return ninjaModelporId.map(ninjaMapper::map).orElse(null); // se houve mapeia com ninjaMapper, se nao retorna null
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
    public NinjaDTO atualizarNinja(Long id, NinjaDTO ninjaDTO) {
        Optional<NinjaModel> ninjaExistente = ninjaRepository.findById(id); // Verifica o ID que o usuario quer atualizar (se existe)
        if(ninjaExistente.isPresent()){ // Se o Id esta la
            NinjaModel ninjaAtualizado = ninjaMapper.map(ninjaDTO); // Pega o DTO de parametro e converte para model
            ninjaAtualizado.setId(id); // Sobreescreve o ID para o novo model
            NinjaModel ninjaSalvo = ninjaRepository.save(ninjaAtualizado); // Salva os dados atualizados no Banco de dados, e retorna a entidade
            return ninjaMapper.map(ninjaSalvo); // converse o ninja salvo para um DTO
        }
        return  null;
    }

}
