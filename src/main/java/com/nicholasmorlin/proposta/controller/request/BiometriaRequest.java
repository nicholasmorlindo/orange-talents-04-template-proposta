package com.nicholasmorlin.proposta.controller.request;

import com.nicholasmorlin.proposta.model.Biometria;
import com.nicholasmorlin.proposta.model.Cartao;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Base64;

public class BiometriaRequest {

    @NotNull
    @NotEmpty
    @Lob
    private byte[] fingerPrint;

    public BiometriaRequest(){
    }

    public BiometriaRequest(@NotNull @NotEmpty byte[] fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public Biometria encodeBiometria(byte[] biometria, Cartao cartao){
        String encodeString = Base64.getEncoder().encodeToString(biometria);
        return new Biometria(encodeString, cartao);
    }

    public byte[] getFingerPrint() {
        return fingerPrint;
    }
}
