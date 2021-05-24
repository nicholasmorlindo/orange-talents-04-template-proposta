package com.nicholasmorlin.proposta.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cartoes")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCartao;

    private Date emitidoEm;
    private String titular;
    private BigDecimal limite;
    private String idProposta;

    @Deprecated
    public Cartao(){
    }

    public Cartao(String numeroCartao, Date emitidoEm, String titular, BigDecimal limite, String idProposta) {
        this.numeroCartao = numeroCartao;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.idProposta = idProposta;
    }

    public Long getId() {
        return id;
    }

    public Date getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
