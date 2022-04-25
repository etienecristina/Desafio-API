package com.gft.controllers;

import com.gft.auth.AutenticacaoDTO;
import com.gft.auth.TokenDTO;
import com.gft.services.AutenticacaoService;
import com.gft.services.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Controller
@RequestMapping("api/auth")
@Api(value = "DESAFIO STARTER - API")
public class AutenticacaoController {

    @Autowired
    AutenticacaoService autenticacaoService;

    @Autowired
    EmailService emailService;

    @PostMapping
    @CrossOrigin(origins = "*")
    @ApiOperation(value = "Salvar um usuário")
    public ResponseEntity<TokenDTO> autenticar(@RequestBody AutenticacaoDTO authForm) {

        try {
            TokenDTO tokenDTO = autenticacaoService.autenticar(authForm);
            emailService.mandarEmail();
            return ResponseEntity.ok(tokenDTO);
        } catch (AuthenticationException ae) {
            ae.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


}