package com.nicholasmorlin.proposta.model;

import com.nicholasmorlin.proposta.controller.response.Status;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "propostas")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpfOrCnpj;
    private String nome;
    private String email;
    private String endereco;
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    private Cartao cartao;

    public Proposta(){
    }

    public Proposta(String cpfOrCnpj, String nome, String email, String endereco, BigDecimal salario) {
        this.cpfOrCnpj = cpfOrCnpj;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}
