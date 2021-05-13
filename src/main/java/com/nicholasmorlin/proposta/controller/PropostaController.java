package com.nicholasmorlin.proposta.controller;

import com.nicholasmorlin.proposta.controller.response.PropostaResponse;
import com.nicholasmorlin.proposta.model.Proposta;
import com.nicholasmorlin.proposta.controller.request.PropostaRequest;
import com.nicholasmorlin.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    private final PropostaRepository propostaRepository;

    @Autowired
    public PropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PropostaResponse cadastrarProposta(@RequestBody @Valid PropostaRequest propostaRequest){

        Proposta proposta = propostaRequest.toModel();
        propostaRepository.save(proposta);

        return PropostaResponse.toResponse(proposta);
    }
}
