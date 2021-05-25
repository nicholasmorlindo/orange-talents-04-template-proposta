package com.nicholasmorlin.proposta.controller.client;

import com.nicholasmorlin.proposta.controller.request.AnaliseCartaoRequest;
import com.nicholasmorlin.proposta.controller.response.AnaliseCartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analiseCartoes", url = "${ASSOCIARCONTAS_API_URL}", configuration = SimpleFeignConfiguration.class)
public interface AnaliseCartoesClient {

    @PostMapping("/api/cartoes")
    AnaliseCartaoResponse consultaCartao(@RequestBody AnaliseCartaoRequest request);
}
