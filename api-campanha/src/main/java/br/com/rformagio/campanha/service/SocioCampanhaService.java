package br.com.rformagio.campanha.service;

import br.com.rformagio.campanha.data.CampanhaData;
import br.com.rformagio.campanha.domain.Campanha;
import br.com.rformagio.campanha.domain.SocioCampanha;
import br.com.rformagio.campanha.domain.Time;
import br.com.rformagio.campanha.persistence.CampanhaRepository;
import br.com.rformagio.campanha.persistence.SocioCampanhaRepository;
import br.com.rformagio.campanha.persistence.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SocioCampanhaService {

    @Autowired
    SocioCampanhaRepository socioCampanhaRepository;

    @Autowired
    CampanhaRepository campanhaRepository;

    @Autowired
    TimeRepository timeRepository;

    public List<CampanhaData> addSocioCampanha(Long socioId, Long timeId){
        Time time = timeRepository.findById(timeId).get();
        List<Campanha> campanhasByTime = campanhaRepository.findByTimeAndDataFimVigenciaAfter(time, LocalDate.now());
        for(Campanha c : campanhasByTime) {
            SocioCampanha socioCampanha = new SocioCampanha();
            socioCampanha.setSocioId(socioId);
            socioCampanha.setCampanhaId(c.getCampanhaId());
            socioCampanhaRepository.save(socioCampanha);
        }

        return convertList(campanhasByTime);
    }

    private List<CampanhaData> convertList(List<Campanha> campanhas){
        List<CampanhaData> campanhaDataList = new ArrayList<>();
        campanhas.forEach(campanha -> {
            CampanhaData cd = CampanhaData.builder()
                    .dataFimVigencia(campanha.getDataFimVigencia())
                    .dataIniVigencia(campanha.getDataIniVigencia())
                    .id(campanha.getCampanhaId())
                    .nomeCampanha(campanha.getNome())
                    .timeId(campanha.getTime().getTimeId())
                    .nomeTime(campanha.getTime().getNome())
                    .build();
            campanhaDataList.add(cd);
        });

        return campanhaDataList;
    }
}
