package com.nicholasmorlin.proposta.controller.request;

public class BloqueioCartaoRequest {

    private String sistemaResponsavel = "propostas";

    public BloqueioCartaoRequest() {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
