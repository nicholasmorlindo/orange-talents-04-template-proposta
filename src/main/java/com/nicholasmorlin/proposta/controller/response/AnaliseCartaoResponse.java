package com.nicholasmorlin.proposta.controller.response;

import com.nicholasmorlin.proposta.model.Cartao;
import com.nicholasmorlin.proposta.model.Proposta;

import java.math.BigDecimal;
import java.util.Date;

public class AnaliseCartaoResponse {

    private String id;
    private Date emitidoEm;
    private String titular;
    private BigDecimal limite;
    private String idProposta;

    public AnaliseCartaoResponse(String id, Date emitidoEm, String titular, BigDecimal limite, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public Cartao toModel() {
        return new Cartao(this.id, this.emitidoEm, this. titular, this.limite, this.idProposta);
    }
}
