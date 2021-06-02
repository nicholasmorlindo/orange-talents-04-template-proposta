package com.nicholasmorlin.proposta.controller.request;

import java.time.LocalDate;

public class AvisoCartaoRequest {

    private String destino;
    private LocalDate validoAte;

    public AvisoCartaoRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
