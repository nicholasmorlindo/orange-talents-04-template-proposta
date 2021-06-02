package com.nicholasmorlin.proposta.controller.response;

import com.nicholasmorlin.proposta.model.Bloqueio;
import java.time.LocalDateTime;

public class BloqueioResponse {

    private Long id;
    private LocalDateTime horarioBloqueio = LocalDateTime.now();
    private String ipAddress;
    private String userAgent;
    private Long cartao;

    public BloqueioResponse(Long id, LocalDateTime horarioBloqueio, String ipAddress, String userAgent, Long cartao) {
        this.id = id;
        this.horarioBloqueio = horarioBloqueio;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public static BloqueioResponse toResponse(Bloqueio bloqueio) {
        return new BloqueioResponse(bloqueio.getId(),
                                    bloqueio.getHorarioBloqueio(),
                                    bloqueio.getIpAddress(),
                                    bloqueio.getUserAgent(),
                                    bloqueio.getCartao().getId());
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

    public Long getCartao() {
        return cartao;
    }
}
