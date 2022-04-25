package com.gft.services;

import com.gft.dto.starter.StarterDTO;
import com.gft.entities.Starter;
import com.gft.repositories.StarterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class StarterService {
    @Autowired
    StarterRepository starterRepository;

    public Starter salvarStarter(Starter starter) {
        return starterRepository.save(starter);
    }

    public Starter buscarStarter(Long id) {
        Optional<Starter> optionalStarter = starterRepository.findById(id);
        return optionalStarter.orElseThrow(() -> new EntityNotFoundException("Não encontrado"));
    }

    public List<Starter> listarTodosOsStarters() {
        return starterRepository.findAll();
    }

    public List<Starter> ordenarPorOrdemCrescente() {
        return starterRepository.findAll(Sort.sort(Starter.class).by((Function<Starter, Object>) starter -> starter.getNome()).ascending());
    }

    public List<Starter> ordenarPorOrdemDecrescente() {
        return starterRepository.findAll(Sort.sort(Starter.class).by((Function<Starter, Object>) starter -> starter.getNome()).descending());
    }

    public List<Starter> buscarStarterPorNome(String nome){
        return starterRepository.findByNome(nome);
    }

    public Starter atualizarStarter(Starter starter, Long id) {
        Starter starterOriginal = this.buscarStarter(id);
        starter.setId(starterOriginal.getId());

        return starterRepository.save(starter);
    }

    public void excluirStarter(Long id) {
        starterRepository.deleteById(id);
    }



}


