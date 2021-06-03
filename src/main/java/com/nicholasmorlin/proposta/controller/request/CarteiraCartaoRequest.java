package com.nicholasmorlin.proposta.controller.request;

import com.nicholasmorlin.proposta.controller.response.TipoCarteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraCartaoRequest {

    @NotNull @NotBlank @Email
    private String email;

    @NotNull
    private TipoCarteira carteira;

    public CarteiraCartaoRequest(String email, TipoCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }
}
