package com.nicholasmorlin.proposta.controller;

import com.nicholasmorlin.proposta.controller.exception.ApiErroException;
import com.nicholasmorlin.proposta.controller.request.BiometriaRequest;
import com.nicholasmorlin.proposta.controller.response.BiometriaResponse;
import com.nicholasmorlin.proposta.model.Biometria;
import com.nicholasmorlin.proposta.model.Cartao;
import com.nicholasmorlin.proposta.repository.BiometriaRepository;
import com.nicholasmorlin.proposta.repository.CartaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/biometrias")
public class BiometriaController {

    private final CartaoRepository cartaoRepository;
    private final BiometriaRepository biometriaRepository;

    public BiometriaController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository) {
        this.cartaoRepository = cartaoRepository;
        this.biometriaRepository = biometriaRepository;
    }

    @PostMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public BiometriaResponse cadastrarBiometria(@PathVariable Long id, @RequestBody @Valid BiometriaRequest biometriaRequest) {

        Optional<Cartao> cartao = cartaoRepository.findById(id);

        if (cartao.isEmpty()) {
            throw new ApiErroException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
        } else {
            Biometria biometria = biometriaRequest.encodeBiometria(biometriaRequest.getFingerPrint(), cartao.get());
            try {
                biometria.setCartao(id);
                biometriaRepository.save(biometria);
            } catch (Exception e) {
                throw new ApiErroException(HttpStatus.BAD_REQUEST, "DADOS INVÁLIDOS");
            }
            return BiometriaResponse.toResponse(biometria);
        }

    }
}
