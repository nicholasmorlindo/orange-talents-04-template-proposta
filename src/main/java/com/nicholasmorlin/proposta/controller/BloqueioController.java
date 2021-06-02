package com.nicholasmorlin.proposta.controller;

import com.nicholasmorlin.proposta.controller.client.AnaliseCartoesClient;
import com.nicholasmorlin.proposta.controller.exception.ApiErroException;
import com.nicholasmorlin.proposta.controller.request.BloqueioCartaoRequest;
import com.nicholasmorlin.proposta.controller.response.BloqueioCartaoResponse;
import com.nicholasmorlin.proposta.controller.response.BloqueioResponse;
import com.nicholasmorlin.proposta.model.Bloqueio;
import com.nicholasmorlin.proposta.model.Cartao;
import com.nicholasmorlin.proposta.repository.BloqueioRepository;
import com.nicholasmorlin.proposta.repository.CartaoRepository;
import feign.FeignException;
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
    private final AnaliseCartoesClient analiseCartoesClient;

    public BloqueioController(BloqueioRepository bloqueioRepository, CartaoRepository cartaoRepository, AnaliseCartoesClient analiseCartoesClient) {
        this.bloqueioRepository = bloqueioRepository;
        this.cartaoRepository = cartaoRepository;
        this.analiseCartoesClient = analiseCartoesClient;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}")
    @Transactional
    public BloqueioResponse bloquearCartao(@PathVariable Long id, HttpServletRequest httpServletRequest){

        Optional<Cartao> cartao = cartaoRepository.findById(id);
        Optional<Bloqueio> verificaBloqueio = bloqueioRepository.findByCartaoBloqueio(id);

        if (cartao.isPresent()) {
            if (verificaBloqueio.isEmpty()) {

                Bloqueio bloqueio = new Bloqueio(httpServletRequest.getRemoteAddr(),
                                                 httpServletRequest.getHeader("User-Agent"),
                                                 cartao.get());

                bloqueioCartao(cartao.get());


                bloqueioRepository.save(bloqueio);
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

    public void bloqueioCartao(Cartao cartao){
        try {
            BloqueioCartaoResponse bloqueioCartaoResponse = analiseCartoesClient.bloquearCartao(cartao.getNumeroCartao(), new BloqueioCartaoRequest());
            cartao.setStatusCartao(bloqueioCartaoResponse.getStatusCartao());
            cartaoRepository.save(cartao);
        } catch (FeignException.BadRequest e) {
            throw new ApiErroException(HttpStatus.BAD_REQUEST, "Algum erro ocorreu");
        } catch (FeignException.InternalServerError e) {
            throw new ApiErroException(HttpStatus.INTERNAL_SERVER_ERROR, "Algum erro ocorreu do lado do servidor");
        }
    }

}
