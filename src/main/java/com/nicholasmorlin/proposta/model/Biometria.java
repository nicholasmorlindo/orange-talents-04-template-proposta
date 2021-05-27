package com.nicholasmorlin.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "biometrias")
public class Biometria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotBlank
    @Lob @Basic(fetch = FetchType.LAZY)
    private String fingerPrint;

    private LocalDateTime dataBiometria = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    @Deprecated
    public Biometria(){
    }

    public Biometria(@NotNull @NotBlank String fingerPrint, Cartao cartao) {
        this.fingerPrint = fingerPrint;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public LocalDateTime getDataBiometria() {
        return dataBiometria;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Long id) {
    }
}
