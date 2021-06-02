package com.nicholasmorlin.proposta.controller;

import com.nicholasmorlin.proposta.controller.exception.ApiErroException;
import com.nicholasmorlin.proposta.controller.request.AvisoViagemRequest;
import com.nicholasmorlin.proposta.controller.response.AvisoViagemResponse;
import com.nicholasmorlin.proposta.model.AvisoViagem;
import com.nicholasmorlin.proposta.model.Cartao;
import com.nicholasmorlin.proposta.repository.AvisoViagemRepository;
import com.nicholasmorlin.proposta.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/viagens")
public class AvisoViagemController {

    private final CartaoRepository cartaoRepository;
    private final AvisoViagemRepository avisoViagemRepository;

    public AvisoViagemController(CartaoRepository cartaoRepository, AvisoViagemRepository avisoViagemRepository) {
        this.cartaoRepository = cartaoRepository;
        this.avisoViagemRepository = avisoViagemRepository;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvisoViagemResponse registrarViagem(@PathVariable Long id, @RequestBody @Valid AvisoViagemRequest avisoViagemRequest, HttpServletRequest httpServletRequest){

        Optional<Cartao> cartao = cartaoRepository.findById(id);

        if (cartao.isPresent()) {
            try {
                AvisoViagem avisoViagem = avisoViagemRequest.ToModel(httpServletRequest, cartao.get());
                avisoViagemRepository.save(avisoViagem);
                return AvisoViagemResponse.toResponse(avisoViagem);
            } catch (Exception e) {
                throw new ApiErroException(HttpStatus.BAD_REQUEST, "Erro na hora de salvar aviso de viagem");
            }
        } else if (cartao.isEmpty()){
            throw new ApiErroException(HttpStatus.NOT_FOUND, "Cartão não encontrado.");
        } else {
            throw new ApiErroException(HttpStatus.BAD_REQUEST, "Erro na hora de salvar aviso de viagem");
        }
    }

}
