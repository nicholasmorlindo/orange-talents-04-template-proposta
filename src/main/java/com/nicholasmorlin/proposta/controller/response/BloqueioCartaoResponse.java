package com.nicholasmorlin.proposta.controller.response;

public class BloqueioCartaoResponse {

    private StatusCartao resultado;

    private BloqueioCartaoResponse(){
    }

    public BloqueioCartaoResponse(StatusCartao statusCartao) {
        this.resultado = statusCartao;
    }

    public StatusCartao getStatusCartao() {
        return resultado;
    }
}
