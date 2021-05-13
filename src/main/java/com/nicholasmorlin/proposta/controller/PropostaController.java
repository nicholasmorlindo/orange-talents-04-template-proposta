package com.nicholasmorlin.proposta.controller;

import com.nicholasmorlin.proposta.controller.exception.ApiErroException;
import com.nicholasmorlin.proposta.controller.response.PropostaResponse;
import com.nicholasmorlin.proposta.model.Proposta;
import com.nicholasmorlin.proposta.controller.request.PropostaRequest;
import com.nicholasmorlin.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/propostas")
public class PropostaController {

    @Autowired
    private final PropostaRepository propostaRepository;

    public PropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PropostaResponse cadastrarProposta(@RequestBody @Valid PropostaRequest propostaRequest){

        Optional<Proposta> propostaCpfOrCnpj =
                propostaRepository.findByCpfOrCnpj(propostaRequest.getCpfOrCnpj());

        if (propostaCpfOrCnpj.isPresent()){
            throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY,"Esse CPF/CNPJ já está vinculado a uma proposta.");
        } else {
            Proposta proposta = propostaRequest.toModel();
            propostaRepository.save(proposta);
            return PropostaResponse.toResponse(proposta);
        }
    }
}
