package com.nicholasmorlin.proposta.controller.request;

import com.nicholasmorlin.proposta.model.AvisoViagem;
import com.nicholasmorlin.proposta.model.Cartao;

import javax.persistence.ManyToOne;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AvisoViagemRequest {

    @NotNull @NotBlank
    private String destinoViagem;

    @NotNull @FutureOrPresent
    private LocalDate terminoViagem;

    private LocalDateTime instanteAvisoViagem = LocalDateTime.now();
    private String ipAddress;
    private String userAgent;
    private Cartao cartao;

    public AvisoViagemRequest(String destinoViagem, LocalDate terminoViagem, String ipAddress, String userAgent, Cartao cartao) {
        this.destinoViagem = destinoViagem;
        this.terminoViagem = terminoViagem;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public AvisoViagem ToModel(HttpServletRequest httpServletRequest, Cartao cartao) {
        return new AvisoViagem(this.destinoViagem,
                               this.terminoViagem,
                               httpServletRequest.getRemoteAddr(),
                               httpServletRequest.getHeader("User-Agent"),
                               cartao);
    }
}
