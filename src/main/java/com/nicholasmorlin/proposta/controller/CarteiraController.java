package com.nicholasmorlin.proposta.controller;

import com.nicholasmorlin.proposta.controller.client.AnaliseCartoesClient;
import com.nicholasmorlin.proposta.controller.exception.ApiErroException;
import com.nicholasmorlin.proposta.controller.request.CarteiraCartaoRequest;
import com.nicholasmorlin.proposta.controller.response.CarteiraCartaoResponse;
import com.nicholasmorlin.proposta.controller.response.CarteiraResponse;
import com.nicholasmorlin.proposta.model.Cartao;
import com.nicholasmorlin.proposta.model.Carteira;
import com.nicholasmorlin.proposta.repository.CartaoRepository;
import com.nicholasmorlin.proposta.repository.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/carteiras")
public class CarteiraController {

    private final CartaoRepository cartaoRepository;
    private final AnaliseCartoesClient analiseCartoesClient;
    private final CarteiraRepository carteiraRepository;

    public CarteiraController(CartaoRepository cartaoRepository, AnaliseCartoesClient analiseCartoesClient, CarteiraRepository carteiraRepository) {
        this.cartaoRepository = cartaoRepository;
        this.analiseCartoesClient = analiseCartoesClient;
        this.carteiraRepository = carteiraRepository;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public CarteiraResponse associaCartao(@PathVariable Long id, @RequestBody @Valid CarteiraCartaoRequest carteiraCartaoRequest){

        Optional<Cartao> cartao = cartaoRepository.findById(id);

        if (cartao.isPresent()) {
            if (cartao.get().getStatusCarteira().toString() != "ASSOCIADA") {

                CarteiraCartaoResponse carteiraCartaoResponse = analiseCartoesClient.associarCarteira(cartao.get().getNumeroCartao(), carteiraCartaoRequest);
                cartao.get().setStatusCarteira(carteiraCartaoResponse.getResultado());
                cartaoRepository.save(cartao.get());

                Carteira carteira = new Carteira(carteiraCartaoRequest.getEmail(), carteiraCartaoResponse.getId(), carteiraCartaoRequest.getCarteira(), cartao.get());
                carteiraRepository.save(carteira);

                return CarteiraResponse.ToResponse(carteira);
            } else {
                throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já está associada a uma carteira");
            }
        } else {
            throw new ApiErroException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
        }
    }
}
