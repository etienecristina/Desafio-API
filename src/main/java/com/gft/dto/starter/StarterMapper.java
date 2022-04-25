package com.gft.dto.starter;

import com.gft.dto.categoria.CategoriaDTO;
import com.gft.entities.Categoria;
import com.gft.entities.Starter;

public class StarterMapper {
    public static Starter fromDTO(StarterDTO dto) {
        return new Starter(null, dto.getNome(), dto.getCpf(), dto.getQuatroLetras(), dto.getEmail());
    }

    public static StarterDTO fromEntity(Starter starter) {
        return new StarterDTO(starter.getId(), starter.getNome(), starter.getCpf(), starter.getQuatroLetras(), starter.getEmail());
    }
}
