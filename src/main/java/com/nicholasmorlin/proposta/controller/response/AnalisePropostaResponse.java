package com.nicholasmorlin.proposta.controller.response;

public class AnalisePropostaResponse {

    private String documento;
    private String nome;
    private String idProposta;

    private ResultadoSolicitacao resultadoSolicitacao;

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public ResultadoSolicitacao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public Status status() {
        return resultadoSolicitacao.getStatus();
    }
}
