package com.gft.dto.starter;

import org.hibernate.validator.constraints.br.CPF;

public class StarterDTO {
    private Long id;
    private String nome;
    @CPF(message = "invalid.document")
    private String cpf;
    private String quatroLetras;
    private String email;

    public StarterDTO() {
    }

    public StarterDTO(Long id, String nome, String cpf, String quatroLetras, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.quatroLetras = quatroLetras;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getQuatroLetras() {
        return quatroLetras;
    }

    public void setQuatroLetras(String quatroLetras) {
        this.quatroLetras = quatroLetras;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
