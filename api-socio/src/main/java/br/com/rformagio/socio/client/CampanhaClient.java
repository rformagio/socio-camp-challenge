package br.com.rformagio.socio.client;

import br.com.rformagio.socio.client.fallback.CampanhaFallback;
import br.com.rformagio.socio.data.CampanhaData;
import br.com.rformagio.socio.data.SocioData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "campanhaClient", url="http://localhost:9090", fallback = CampanhaFallback.class)
public interface CampanhaClient {

    @PostMapping("/api/v1/sociocampanhas")
    List<CampanhaData> addCampanhas(SocioData socioData);

}
