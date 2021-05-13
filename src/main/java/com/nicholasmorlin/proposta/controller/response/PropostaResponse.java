package com.nicholasmorlin.proposta.controller.response;

import com.nicholasmorlin.proposta.model.Proposta;

import java.math.BigDecimal;

public class PropostaResponse {

    private Long id;
    private String cpfOrCnpj;
    private String nome;
    private String email;
    private String endereco;
    private BigDecimal salario;

    public PropostaResponse(Long id, String cpfOrCnpj, String nome, String email, String endereco, BigDecimal salario) {
        this.id = id;
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

    public static PropostaResponse toResponse(Proposta proposta) {
        return new PropostaResponse(
                proposta.getId(),
                proposta.getCpfOrCnpj(),
                proposta.getNome(),
                proposta.getEmail(),
                proposta.getEndereco(),
                proposta.getSalario()
        );
    }


}
