package com.nicholasmorlin.proposta.controller.client;

import com.nicholasmorlin.proposta.controller.request.AnaliseCartaoRequest;
import com.nicholasmorlin.proposta.controller.request.BloqueioCartaoRequest;
import com.nicholasmorlin.proposta.controller.response.AnaliseCartaoResponse;
import com.nicholasmorlin.proposta.controller.response.BloqueioCartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analiseCartoes", url = "${ASSOCIARCONTAS_API_URL}", configuration = SimpleFeignConfiguration.class)
public interface AnaliseCartoesClient {

    @PostMapping("/api/cartoes")
    AnaliseCartaoResponse consultaCartao(@RequestBody AnaliseCartaoRequest request);

    @PostMapping("/api/cartoes/{numeroCartao}/bloqueios")
    BloqueioCartaoResponse bloquearCartao(@PathVariable String numeroCartao, @RequestBody BloqueioCartaoRequest bloqueioCartaoRequest);
}
