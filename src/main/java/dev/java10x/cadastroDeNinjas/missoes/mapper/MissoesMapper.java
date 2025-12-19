package dev.java10x.cadastroDeNinjas.missoes.mapper;

import dev.java10x.cadastroDeNinjas.missoes.dtos.MissoesDTO;
import dev.java10x.cadastroDeNinjas.missoes.model.MissoesModel;
import org.springframework.stereotype.Component;

@Component
public class MissoesMapper {

    public MissoesDTO map(MissoesModel missoesModel){
        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setId(missoesModel.getId());
        missoesDTO.setNome(missoesModel.getNome());
        missoesDTO.setDificuldade(missoesModel.getDificuldade());
        missoesDTO.setNinjas(missoesModel.getNinjas());

        return missoesDTO;
    }

    public MissoesModel map(MissoesDTO missoesDTO){
        MissoesModel missoesModel = new MissoesModel();
        missoesModel.setId(missoesModel.getId());
        missoesModel.setNome(missoesDTO.getNome());
        missoesModel.setDificuldade(missoesDTO.getDificuldade());
        missoesModel.setNinjas(missoesDTO.getNinjas());

        return missoesModel;
    }


}
