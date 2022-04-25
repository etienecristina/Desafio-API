package com.gft.dto.categoria;

public class CategoriaDTO {
    private Long id;
    private String tecnologia;
    private String nome;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Long id, String tecnologiaCategoria, String nomeCategoria) {
        this.id = id;
        this.tecnologia = tecnologiaCategoria;
        this.nome = nomeCategoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
