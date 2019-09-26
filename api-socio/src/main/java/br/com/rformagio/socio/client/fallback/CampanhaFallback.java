package br.com.rformagio.socio.client.fallback;

import br.com.rformagio.socio.client.CampanhaClient;
import br.com.rformagio.socio.data.CampanhaData;
import br.com.rformagio.socio.data.SocioData;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CampanhaFallback implements CampanhaClient {

    @Override
    public List<CampanhaData> addCampanhas(SocioData socioData) {
        List<CampanhaData> campanhaDataList = new ArrayList<>();

        campanhaDataList.add(CampanhaData.builder()
        .nomeTime("Time cache")
        .dataFimVigencia(LocalDate.now())
        .dataIniVigencia(LocalDate.now().plusDays(1))
        .timeId(1L).nomeCampanha("Campanha Cache")
        .build());

        return campanhaDataList;
    }
}
