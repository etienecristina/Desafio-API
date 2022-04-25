package com.gft.services;

import com.gft.entities.Categoria;
import com.gft.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria salvarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria buscarCategoria(Long id) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        return optionalCategoria.orElseThrow(() -> new EntityNotFoundException("Não encontrada"));
    }

    public List<Categoria> listarTodasAsCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria atualizarCategoria(Categoria categoria, Long id) {
        Categoria categoriaOriginal = this.buscarCategoria(id);
        categoria.setId(categoriaOriginal.getId());

        return categoriaRepository.save(categoria);
    }

    public void excluirCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}
