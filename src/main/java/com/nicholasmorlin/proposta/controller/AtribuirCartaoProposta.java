package com.nicholasmorlin.proposta.controller;

import com.nicholasmorlin.proposta.controller.client.AnaliseCartoesClient;
import com.nicholasmorlin.proposta.controller.exception.ApiErroException;
import com.nicholasmorlin.proposta.controller.request.AnaliseCartaoRequest;
import com.nicholasmorlin.proposta.controller.response.AnaliseCartaoResponse;
import com.nicholasmorlin.proposta.model.Cartao;
import com.nicholasmorlin.proposta.model.Proposta;
import com.nicholasmorlin.proposta.repository.CartaoRepository;
import com.nicholasmorlin.proposta.repository.PropostaRepository;
import feign.FeignException;
import feign.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AtribuirCartaoProposta {

    private final PropostaRepository propostaRepository;

    private final AnaliseCartoesClient analiseCartoesClient;

    private final CartaoRepository cartaoRepository;

    public AtribuirCartaoProposta(PropostaRepository propostaRepository, AnaliseCartoesClient analiseCartoesClient, CartaoRepository cartaoRepository) {
        this.propostaRepository = propostaRepository;
        this.analiseCartoesClient = analiseCartoesClient;
        this.cartaoRepository = cartaoRepository;
    }

    @Scheduled(fixedDelay = 5000)
    public void associarCartaoProposta(){

        List<Proposta> propostas = new ArrayList<>();

        propostas = propostaRepository.findByCartaoIsNullAndStatusElegivel();

        propostas.forEach(proposta -> {
                    try {
                        AnaliseCartaoRequest analiseCartaoRequest = new AnaliseCartaoRequest(proposta.getCpfOrCnpj(),
                                                                                             proposta.getNome(),
                                                                                             proposta.getId());

                        AnaliseCartaoResponse analiseCartaoResponse = analiseCartoesClient.consultaCartao(analiseCartaoRequest);

                        Cartao cartao = analiseCartaoResponse.toModel();
                        cartaoRepository.save(cartao);

                        proposta.setCartao(cartao);

                        propostaRepository.save(proposta);

                    } catch (Exception e) {
                        throw new ApiErroException(HttpStatus.BAD_REQUEST, "Erro, aguardar próxima interação");
                    }
        });

    }

}
