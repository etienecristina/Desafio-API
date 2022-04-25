package com.gft.controllers;

import com.gft.dto.categoria.CategoriaDTO;
import com.gft.dto.categoria.CategoriaMapper;
import com.gft.dto.starter.StarterMapper;
import com.gft.entities.Categoria;
import com.gft.services.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/categorias")
@Api(value = "DESAFIO STARTER - API")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    @CrossOrigin(origins = "admin")
    @ApiOperation(value = "Salvar uma categoria", authorizations = { @Authorization(value="Authorization") })
    public ResponseEntity<CategoriaDTO> salvarCategoria(@RequestBody CategoriaDTO dto) {
        Categoria categoria = categoriaService.salvarCategoria(CategoriaMapper.fromDTO(dto));
        return ResponseEntity.ok(CategoriaMapper.fromEntity(categoria));
    }

    @GetMapping("{id}")
    @CrossOrigin(origins = "*")
    @ApiOperation(value = "Buscar uma categoria única", authorizations = { @Authorization(value="Authorization") })
    public ResponseEntity<CategoriaDTO> buscarCategoria(@PathVariable Long id) {
        Categoria categoria = categoriaService.buscarCategoria(id);

        return ResponseEntity.ok(CategoriaMapper.fromEntity(categoria));
    }

    @GetMapping
    @CrossOrigin(origins = "*")
    @ApiOperation(value = "Listar todas as categorias salvas", authorizations = { @Authorization(value="Authorization") })
    public ResponseEntity<List<CategoriaDTO>> buscarTodasAsCategorias() {
        return ResponseEntity.ok(categoriaService.listarTodasAsCategorias().stream().map(CategoriaMapper::fromEntity).collect(Collectors.toList()));
    }

    @PutMapping("{id}")
    @CrossOrigin(origins = "admin")
    @ApiOperation(value = "Atualizar os dados de uma categoria", authorizations = { @Authorization(value="Authorization") })
    public ResponseEntity<CategoriaDTO> atualizarCategoria(@RequestBody CategoriaDTO dto,
                                                           @PathVariable Long id) {
        try {
            Categoria categoria = categoriaService.atualizarCategoria(CategoriaMapper.fromDTO(dto), id);
            return ResponseEntity.ok(CategoriaMapper.fromEntity(categoria));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    @CrossOrigin(origins = "admin")
    @ApiOperation(value = "Deletar uma categoria", authorizations = { @Authorization(value="Authorization") })
    public ResponseEntity<CategoriaDTO> excluirCategoria(@PathVariable Long id) {
        try {
            categoriaService.excluirCategoria(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
