package com.nicholasmorlin.proposta.controller;

import com.nicholasmorlin.proposta.controller.client.AnaliseCartoesClient;
import com.nicholasmorlin.proposta.controller.exception.ApiErroException;
import com.nicholasmorlin.proposta.controller.request.AvisoCartaoRequest;
import com.nicholasmorlin.proposta.controller.request.AvisoViagemRequest;
import com.nicholasmorlin.proposta.controller.request.BloqueioCartaoRequest;
import com.nicholasmorlin.proposta.controller.response.AvisoCartaoResponse;
import com.nicholasmorlin.proposta.controller.response.AvisoViagemResponse;
import com.nicholasmorlin.proposta.controller.response.BloqueioCartaoResponse;
import com.nicholasmorlin.proposta.model.AvisoViagem;
import com.nicholasmorlin.proposta.model.Cartao;
import com.nicholasmorlin.proposta.repository.AvisoViagemRepository;
import com.nicholasmorlin.proposta.repository.CartaoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/viagens")
public class AvisoViagemController {

    private final CartaoRepository cartaoRepository;
    private final AvisoViagemRepository avisoViagemRepository;
    private final AnaliseCartoesClient analiseCartoesClient;

    public AvisoViagemController(CartaoRepository cartaoRepository, AvisoViagemRepository avisoViagemRepository, AnaliseCartoesClient analiseCartoesClient) {
        this.cartaoRepository = cartaoRepository;
        this.avisoViagemRepository = avisoViagemRepository;
        this.analiseCartoesClient = analiseCartoesClient;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public AvisoViagemResponse registrarViagem(@PathVariable Long id, @RequestBody @Valid AvisoViagemRequest avisoViagemRequest, HttpServletRequest httpServletRequest){

        Optional<Cartao> cartao = cartaoRepository.findById(id);

        if (cartao.isPresent()) {
            try {
                AvisoViagem avisoViagem = avisoViagemRequest.ToModel(httpServletRequest, cartao.get());
                avisoCartao(cartao.get(),avisoViagem);
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

    public void avisoCartao(Cartao cartao, AvisoViagem avisoViagem){
        try {
            AvisoCartaoResponse avisoCartaoResponse = analiseCartoesClient.avisoCartao(cartao.getNumeroCartao(), new AvisoCartaoRequest(avisoViagem.getDestinoViagem(),avisoViagem.getTerminoViagem()));
            cartao.setStatusAviso(avisoCartaoResponse.getResultado());
            cartaoRepository.save(cartao);
        } catch (FeignException.BadRequest e) {
            throw new ApiErroException(HttpStatus.BAD_REQUEST, "Algum erro ocorreu");
        } catch (FeignException.InternalServerError e) {
            throw new ApiErroException(HttpStatus.INTERNAL_SERVER_ERROR, "Algum erro ocorreu do lado do servidor");
        }
    }

}
