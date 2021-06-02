package com.nicholasmorlin.proposta.model;

import com.nicholasmorlin.proposta.controller.response.StatusAviso;
import com.nicholasmorlin.proposta.controller.response.StatusCartao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "cartao")
    private List<Biometria> biometrias;

    @OneToOne(mappedBy = "cartao")
    private Bloqueio bloqueio;

    @Enumerated(value = EnumType.STRING)
    private StatusCartao statusCartao = StatusCartao.ATIVO;

    @Enumerated(value = EnumType.STRING)
    private StatusAviso statusAviso = null;

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

    public Bloqueio getBloqueio() {
        return bloqueio;
    }

    public StatusCartao getStatusCartao() {
        return statusCartao;
    }

    public StatusAviso getStatusAviso() {
        return statusAviso;
    }

    public void setStatusCartao(StatusCartao statusCartao) {
        this.statusCartao = StatusCartao.BLOQUEADO;
    }

    public void setStatusAviso(StatusAviso statusAviso) {
        this.statusAviso = statusAviso;
    }
}
