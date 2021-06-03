package com.nicholasmorlin.proposta.model;

import com.nicholasmorlin.proposta.controller.response.TipoCarteira;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "carteiras")
public class Carteira {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotBlank @Email
    private String email;

    @NotNull
    private String idCarteira;

    @Enumerated(value = EnumType.STRING)
    private TipoCarteira tipoCarteira;

    @ManyToOne
    private Cartao cartao;

    public Carteira(){
    }

    public Carteira(String email, String idCarteira, TipoCarteira tipoCarteira, Cartao cartao) {
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

    public Cartao getCartao() {
        return cartao;
    }

    public TipoCarteira getTipoCarteira() {
        return tipoCarteira;
    }
}
