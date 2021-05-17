package com.nicholasmorlin.proposta.controller.client;

import com.nicholasmorlin.proposta.controller.request.AnalisePropostaRequest;
import com.nicholasmorlin.proposta.controller.response.AnalisePropostaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analiseFinanceira", url = "http://localhost:9999", configuration = SimpleFeignConfiguration.class)
public interface AnaliseFinanceiraClient {

    @PostMapping("/api/solicitacao")
    AnalisePropostaResponse consultar(@RequestBody AnalisePropostaRequest request);

}
