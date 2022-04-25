package com.gft.dto.categoria;

import com.gft.entities.Categoria;

public class CategoriaMapper {
    public static Categoria fromDTO(CategoriaDTO dto) {
        return new Categoria(null, dto.getNome(), dto.getTecnologia());
    }

    public static CategoriaDTO fromEntity(Categoria categoria) {
        return new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getTecnologia());
    }
}

