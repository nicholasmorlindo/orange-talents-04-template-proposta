package com.nicholasmorlin.proposta.controller.response;

import com.nicholasmorlin.proposta.model.Cartao;
import com.nicholasmorlin.proposta.model.Carteira;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraResponse {

    private Long id;
    private String email;
    private String idCarteira;
    private TipoCarteira tipoCarteira;
    private Long cartao;

    public CarteiraResponse(){
    }

    public CarteiraResponse(Long id, String email, String idCarteira, TipoCarteira tipoCarteira, Long cartao) {
        this.id = id;
        this.email = email;
        this.idCarteira = idCarteira;
        this.tipoCarteira = tipoCarteira;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getIdCarteira() {
        return idCarteira;
    }

    public TipoCarteira getTipoCarteira() {
        return tipoCarteira;
    }

    public Long getCartao() {
        return cartao;
    }

    public static CarteiraResponse ToResponse(Carteira carteira) {
        return new CarteiraResponse(carteira.getId(),
                carteira.getEmail(),
                carteira.getIdCarteira(),
                carteira.getTipoCarteira(),
                carteira.getCartao().getId());
    }
}
