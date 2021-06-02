package com.nicholasmorlin.proposta.controller.response;

public class AvisoCartaoResponse {

    private StatusAviso resultado;

    public AvisoCartaoResponse(){
    }

    public AvisoCartaoResponse(StatusAviso resultado) {
        this.resultado = resultado;
    }

    public StatusAviso getResultado() {
        return resultado;
    }
}
