package com.gft.controllers;

import com.gft.entities.Starter;
import com.gft.services.StarterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.IOException;

@Controller
@RequestMapping("api/upload")
@Api(value = "DESAFIO STARTER - API")
public class UploadController {
    @Autowired
    StarterService starterService;

    @CrossOrigin(origins = "*")
    @ApiOperation(value = "Adicionar foto para um Starter", authorizations = {@Authorization(value = "Authorization")})
    @RequestMapping(value = "/starter/{id}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> submit(@RequestBody MultipartFile file, @PathVariable Long id) {
        Starter starter = starterService.buscarStarter(id);
        try {
            starter.setImagem(file.getBytes());
            starterService.atualizarStarter(starter, id);

            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
