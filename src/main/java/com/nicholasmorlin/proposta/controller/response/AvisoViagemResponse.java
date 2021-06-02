package com.nicholasmorlin.proposta.controller.response;

import com.nicholasmorlin.proposta.model.AvisoViagem;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AvisoViagemResponse {

    private Long id;
    private String destinoViagem;
    private LocalDate terminoViagem;
    private LocalDateTime instanteAvisoViagem = LocalDateTime.now();
    private String ipAddress;
    private String userAgent;
    private Long cartao;

    public AvisoViagemResponse(){
    }

    public AvisoViagemResponse(Long id, String destinoViagem, LocalDate terminoViagem, LocalDateTime instanteAvisoViagem, String ipAddress, String userAgent, Long cartao) {
        this.id = id;
        this.destinoViagem = destinoViagem;
        this.terminoViagem = terminoViagem;
        this.instanteAvisoViagem = instanteAvisoViagem;
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

    public Long getCartao() {
        return cartao;
    }

    public static AvisoViagemResponse toResponse(AvisoViagem avisoViagem) {
        return new AvisoViagemResponse(avisoViagem.getId(),
                                       avisoViagem.getDestinoViagem(),
                                       avisoViagem.getTerminoViagem(),
                                       avisoViagem.getInstanteAvisoViagem(),
                                       avisoViagem.getIpAddress(),
                                       avisoViagem.getUserAgent(),
                                       avisoViagem.getCartao().getId());
    }
}
