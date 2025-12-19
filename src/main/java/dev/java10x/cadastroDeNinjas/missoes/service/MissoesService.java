package dev.java10x.cadastroDeNinjas.missoes.service;

import dev.java10x.cadastroDeNinjas.missoes.dtos.MissoesDTO;
import dev.java10x.cadastroDeNinjas.missoes.mapper.MissoesMapper;
import dev.java10x.cadastroDeNinjas.missoes.model.MissoesModel;
import dev.java10x.cadastroDeNinjas.missoes.repository.MissoesRepository;
import dev.java10x.cadastroDeNinjas.ninjas.model.NinjaModel;
import dev.java10x.cadastroDeNinjas.ninjas.repository.NinjaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    @Autowired
    private NinjaRepository ninjaRepository;
    private final MissoesRepository missoesRepository;
    private final MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    public MissoesDTO criar(MissoesDTO missoesDTO){
        MissoesModel missoesModel = missoesMapper.map(missoesDTO); //Primeiro converte de DTO -> MODEL
        missoesModel = missoesRepository.save(missoesModel);
        return missoesMapper.map(missoesModel);
    }

    public List<MissoesDTO> listar(){
        List<MissoesModel> lista = missoesRepository.findAll();
        return lista.stream() //transformar ListaModel em Stream
                .map(missoesMapper::map) // Mapear e o for each usa o map em cada um e vira DTO dnv
                .collect(Collectors.toList()); // Transforma tudo em lista novamente

    }

    public MissoesDTO buscarPorId(Long id){
        Optional<MissoesModel> model = missoesRepository.findById(id); // Evita NULL POINTER, OPTIONAL ja verifica se tem OU NAO
        return model.map(missoesMapper::map).orElse(null);
    }

    public MissoesDTO update(Long id, MissoesDTO missoesDTO){
        Optional<MissoesModel> missoesModel = missoesRepository.findById(id);
        if(missoesModel.isPresent()){ // Se existir (e nao voltar um OPTIONAL)
            MissoesModel atualizado = missoesMapper.map(missoesDTO); // sabendo que existe o MODEL com ID indicado, coloco converto o DTO que recebi para MODEL
            atualizado.setId(id); // Seto o ID que eu recebi na nova entidade (Para nao criar um novo ID)
            MissoesModel salvo = missoesRepository.save(atualizado); // Salvo a nova entidade e coloco em uma variavel para transformar em DTO na resposta
            return missoesMapper.map(salvo); // Converto para DTO de volta
        }
        return null;
    }

    @Transactional // Faz com que todas as operacoes sejam uma coisa so, se falha UM tudo falha (nenhum dado Parcial eh perdido)
    public void delete(Long id){
        MissoesModel missoesModel = missoesRepository.findById(id).orElseThrow(() -> new RuntimeException("Missao Nao encontrada"));
        // Quebra o vinculo dos ninjas
        for(NinjaModel ninja : missoesModel.getNinjas()){ //Pego os ninjas dessa missao e coloco em cada iteracao
            ninja.setMissoes(null); // seto missoes como NULL, acabou a relacao
        }

        ninjaRepository.saveAll(missoesModel.getNinjas()); //Salva os ninjas, deixando a missao nulla
        missoesRepository.delete(missoesModel); // finalmente deleta a missao

    }







}
