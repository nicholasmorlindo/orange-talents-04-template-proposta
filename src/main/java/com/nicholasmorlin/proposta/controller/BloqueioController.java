package com.nicholasmorlin.proposta.controller;

import com.nicholasmorlin.proposta.controller.exception.ApiErroException;
import com.nicholasmorlin.proposta.controller.response.BloqueioResponse;
import com.nicholasmorlin.proposta.model.Bloqueio;
import com.nicholasmorlin.proposta.model.Cartao;
import com.nicholasmorlin.proposta.repository.BloqueioRepository;
import com.nicholasmorlin.proposta.repository.CartaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/bloqueios")
public class BloqueioController {

    private final BloqueioRepository bloqueioRepository;
    private final CartaoRepository cartaoRepository;

    public BloqueioController(BloqueioRepository bloqueioRepository, CartaoRepository cartaoRepository) {
        this.bloqueioRepository = bloqueioRepository;
        this.cartaoRepository = cartaoRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}")
    @Transactional
    public BloqueioResponse bloquearCartao(@PathVariable Long id, HttpServletRequest httpServletRequest){

        Optional<Cartao> cartao = cartaoRepository.findById(id);

        if (cartao.isPresent()) {
            if (cartao.get().getBloqueio() == null) {


                Bloqueio bloqueio = new Bloqueio(httpServletRequest.getRemoteAddr(),
                                                 httpServletRequest.getHeader("User-Agent"),
                                                 cartao.get());
                bloqueioRepository.save(bloqueio);
                cartao.get().setBloqueio(bloqueio.getId());
                cartaoRepository.save(cartao.get());

                return BloqueioResponse.toResponse(bloqueio);

            } else {
                throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já está bloqueado.");
            }
        } else if (cartao.isEmpty()){
            throw new ApiErroException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
        } else {
            throw new ApiErroException(HttpStatus.BAD_REQUEST, "Erro de validação");
        }


    }

}
