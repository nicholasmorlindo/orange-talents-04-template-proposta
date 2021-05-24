package com.nicholasmorlin.proposta.controller;

import com.nicholasmorlin.proposta.controller.client.AnaliseFinanceiraClient;
import com.nicholasmorlin.proposta.controller.exception.ApiErroException;
import com.nicholasmorlin.proposta.controller.request.AnalisePropostaRequest;
import com.nicholasmorlin.proposta.controller.response.AnalisePropostaResponse;
import com.nicholasmorlin.proposta.controller.response.PropostaResponse;
import com.nicholasmorlin.proposta.controller.response.Status;
import com.nicholasmorlin.proposta.model.Proposta;
import com.nicholasmorlin.proposta.controller.request.PropostaRequest;
import com.nicholasmorlin.proposta.repository.PropostaRepository;
import feign.FeignException;
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

    @Autowired
    private final AnaliseFinanceiraClient analiseFinanceiraClient;

    public PropostaController(PropostaRepository propostaRepository, AnaliseFinanceiraClient analiseFinanceiraClient) {
        this.propostaRepository = propostaRepository;
        this.analiseFinanceiraClient = analiseFinanceiraClient;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PropostaResponse consultarProposta(@PathVariable Long id) {

        Optional<Proposta> proposta = propostaRepository.findById(id);

        if (proposta.isPresent()) {
            return PropostaResponse.toResponse(proposta.get());
        }

        throw new ApiErroException(HttpStatus.NOT_FOUND, "Proposta não encontrada");
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

            try {
                AnalisePropostaRequest analisaPropostaRequest = new AnalisePropostaRequest(proposta.getCpfOrCnpj(),
                                                                                            proposta.getNome(),
                                                                                            proposta.getId()
                                                                                            );

                AnalisePropostaResponse resultadoConsulta = analiseFinanceiraClient.consultar(analisaPropostaRequest);
                Status status = resultadoConsulta.status();
                proposta.setStatus(status);

            } catch (FeignException.UnprocessableEntity unprocessableEntity) {
                proposta.setStatus(Status.NAO_ELEGIVEL);
            }
            propostaRepository.save(proposta);

            return PropostaResponse.toResponse(proposta);
        }
    }
}
