package com.gft.controllers;

import com.gft.dto.starter.StarterDTO;
import com.gft.dto.starter.StarterMapper;
import com.gft.entities.Starter;
import com.gft.services.StarterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/starter")
@Api(value = "DESAFIO STARTER - API")
public class StarterController {
    private final StarterService starterService;

    public StarterController(StarterService starterService) {
        this.starterService = starterService;
    }

    @PostMapping
    @CrossOrigin(origins = "admin")
    @ApiOperation(value = "Salvar um Starter", authorizations = { @Authorization(value="Authorization") })
    public ResponseEntity<StarterDTO> salvarStarter(@Valid @RequestBody StarterDTO dto) {
        Starter starter = starterService.salvarStarter(StarterMapper.fromDTO(dto));

        return ResponseEntity.ok(StarterMapper.fromEntity(starter));
    }

    @GetMapping("{id}")
    @CrossOrigin(origins = "*")
    @ApiOperation(value = "Buscar um Starter por ID", authorizations = { @Authorization(value="Authorization") })
    public ResponseEntity<StarterDTO> buscarStarter(@PathVariable Long id) {
        Starter starter = starterService.buscarStarter(id);
        return ResponseEntity.ok(StarterMapper.fromEntity(starter));
    }

    @GetMapping("/nome/{nome}")
    @CrossOrigin(origins = "*")
    @ApiOperation(value = "Buscar um Starter por nome", authorizations = { @Authorization(value="Authorization") })
    public ResponseEntity<List<StarterDTO>> buscarStarterPorNome(@PathVariable String nome) {
        List<Starter> starter = starterService.buscarStarterPorNome(nome);

        return ResponseEntity.ok(starterService.buscarStarterPorNome(nome).stream().map(StarterMapper::fromEntity).collect(Collectors.toList()));
    }

    @GetMapping
    @CrossOrigin(origins = "*")
    @ApiOperation(value = "Listar todos os Starters", authorizations = { @Authorization(value="Authorization") })
    public ResponseEntity<List<StarterDTO>> buscarTodasOsStarters() {
        return ResponseEntity.ok(starterService.listarTodosOsStarters().stream().map(StarterMapper::fromEntity).collect(Collectors.toList()));
    }

    @GetMapping("/asc")
    @CrossOrigin(origins = "*")
    @ApiOperation(value = "Listar todos os Starters em ordem crescente", authorizations = { @Authorization(value="Authorization") })
    public ResponseEntity<List<StarterDTO>> buscarTodosOsStartersOrdemCrescente() {
        return ResponseEntity.ok(starterService.ordenarPorOrdemCrescente().stream().map(StarterMapper::fromEntity).collect(Collectors.toList()));
    }

    @GetMapping("/desc")
    @CrossOrigin(origins = "*")
    @ApiOperation(value = "Listar todos os Starters em ordem decrescente", authorizations = { @Authorization(value="Authorization") })
    public ResponseEntity<List<StarterDTO>> buscarTodosOsStartersOrdemDecrescente() {
        return ResponseEntity.ok(starterService.ordenarPorOrdemDecrescente().stream().map(StarterMapper::fromEntity).collect(Collectors.toList()));
    }

    @PutMapping("{id}")
    @CrossOrigin(origins = "admin")
    @ApiOperation(value = "Atualizar os dados de um Starter", authorizations = { @Authorization(value="Authorization") })
    public ResponseEntity<StarterDTO> atualizarStarter(@RequestBody StarterDTO dto,
                                                       @PathVariable Long id) {
        try {
            Starter starter = starterService.atualizarStarter(StarterMapper.fromDTO(dto), id);
            return ResponseEntity.ok(StarterMapper.fromEntity(starter));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    @CrossOrigin(origins = "admin")
    @ApiOperation(value = "Excluir um Starter", authorizations = { @Authorization(value="Authorization") })
    public ResponseEntity<StarterDTO> excluirStarter(@PathVariable Long id) {
        try {
            starterService.excluirStarter(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
