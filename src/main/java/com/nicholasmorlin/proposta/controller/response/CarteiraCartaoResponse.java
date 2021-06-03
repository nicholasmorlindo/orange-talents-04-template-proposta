package com.nicholasmorlin.proposta.controller.response;

public class CarteiraCartaoResponse {

    private StatusCarteira resultado;
    private String id;

    public CarteiraCartaoResponse(){
    }

    public CarteiraCartaoResponse(StatusCarteira resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public StatusCarteira getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
