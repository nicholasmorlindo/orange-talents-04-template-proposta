package com.nicholasmorlin.proposta.controller.request;

import com.nicholasmorlin.proposta.model.Proposta;
import com.nicholasmorlin.proposta.validation.custom.CpfOrCnpj;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank @NotNull @CpfOrCnpj
    private String cpfOrCnpj;

    @NotBlank @NotNull
    private String nome;

    @NotBlank @Email @NotNull
    private String email;

    @NotBlank @NotNull
    private String endereco;

    @NotNull @Positive
    private BigDecimal salario;

    public PropostaRequest(String cpfOrCnpj, String nome, String email, String endereco, BigDecimal salario) {
        this.cpfOrCnpj = cpfOrCnpj;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public Proposta toModel() {
        return new Proposta(this.cpfOrCnpj, this.nome, this.email, this.endereco, this.salario);
    }
}
