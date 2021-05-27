package com.nicholasmorlin.proposta.controller.response;

import com.nicholasmorlin.proposta.model.Biometria;
import com.nicholasmorlin.proposta.model.Cartao;

import java.time.LocalDateTime;

public class BiometriaResponse {

    private Long id;
    private String fingerPrint;
    private Long cartao;
    private LocalDateTime dataBiometria;

    public BiometriaResponse(Long id, String fingerPrint, LocalDateTime dataBiometria, Long cartao) {
        this.id = id;
        this.fingerPrint = fingerPrint;
        this.dataBiometria = dataBiometria;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public Long getCartao() {
        return cartao;
    }

    public LocalDateTime getDataBiometria() {
        return dataBiometria;
    }

    public static BiometriaResponse toResponse(Biometria biometria) {
        return new BiometriaResponse(biometria.getId(),
                             biometria.getFingerPrint(),
                             biometria.getDataBiometria(),
                             biometria.getCartao().getId());
    }
}
