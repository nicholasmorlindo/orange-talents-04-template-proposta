package com.nicholasmorlin.proposta.model;

import org.springframework.scheduling.annotation.EnableAsync;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "bloqueios")
public class Bloqueio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime horarioBloqueio = LocalDateTime.now();

    @NotNull
    private String ipAddress;

    @NotNull
    private String userAgent;

    @OneToOne @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    @Deprecated
    public Bloqueio(){
    }

    public Bloqueio(String ipAddress, String userAgent, Cartao cartao) {
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getHorarioBloqueio() {
        return horarioBloqueio;
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
