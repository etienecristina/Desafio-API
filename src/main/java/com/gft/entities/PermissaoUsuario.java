package com.gft.entities;

import org.springframework.security.core.GrantedAuthority;

public class PermissaoUsuario implements GrantedAuthority {
    private String permissao;

    public PermissaoUsuario(String permissao) {
        this.permissao = permissao;
    }

    @Override
    public String getAuthority() {
        return permissao;
    }
}
