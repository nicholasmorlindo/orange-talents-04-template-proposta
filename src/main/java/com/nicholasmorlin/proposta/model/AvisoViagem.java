package com.nicholasmorlin.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "aviso_viagens")
public class AvisoViagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotBlank
    private String destinoViagem;

    @NotNull @FutureOrPresent
    private LocalDate terminoViagem;

    private LocalDateTime instanteAvisoViagem = LocalDateTime.now();
    private String ipAddress;
    private String userAgent;

    @ManyToOne
    private Cartao cartao;

    public AvisoViagem(){
    }

    public AvisoViagem(String destinoViagem, LocalDate terminoViagem, String ipAddress, String userAgent, Cartao cartao) {
        this.destinoViagem = destinoViagem;
        this.terminoViagem = terminoViagem;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }

    public LocalDateTime getInstanteAvisoViagem() {
        return instanteAvisoViagem;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
