package br.com.rformagio.campanha.service;

import br.com.rformagio.campanha.data.CampanhaData;
import br.com.rformagio.campanha.domain.Campanha;
import br.com.rformagio.campanha.domain.Time;
import br.com.rformagio.campanha.exception.BusinessException;
import br.com.rformagio.campanha.persistence.CampanhaRepository;
import br.com.rformagio.campanha.persistence.TimeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CampanhaService {

    @Autowired
    CampanhaRepository campanhaRepository;

    @Autowired
    TimeRepository timeRepository;

    public List<CampanhaData> findByDataFimVigenciaGreaterThanEqual(LocalDate date){
        return campanhaRepository.findByDataFimVigenciaGreaterThanEqual(date)
                .stream()
                .map(c -> buildCampanhaData(c))
                .collect(Collectors.toList());
    }

    public CampanhaData findById(Long id){
        CampanhaData campanhaData = null;
        Optional<Campanha> campanha = campanhaRepository.findById(id);
        if(campanha.isPresent()){
            campanhaData = buildCampanhaData(campanha.get());
        }
        return  campanhaData;
    }

    public void createCampanha(CampanhaData campanha) throws BusinessException {

        Optional<Time> time = timeRepository.findById(campanha.getTimeId());
        if(!time.isPresent())
            throw new BusinessException("Time não existe! [id: "+ campanha.getTimeId() +" ]");

        verificaAjustaPeriodo(campanha);
        campanhaRepository.save(buildCampanha(campanha));

    }

    public void updateCampanha(CampanhaData campanha){
        try {
            verificaAjustaPeriodo(campanha);
            Optional<Campanha> campOptional = campanhaRepository.findById(campanha.getId());
            if(campOptional.isPresent()) {
                Campanha campanhaUpdate = campOptional.get();
                campanhaUpdate.setNome(campanha.getNomeCampanha());
                campanhaUpdate.setDataIniVigencia(campanha.getDataIniVigencia());
                campanhaUpdate.setDataFimVigencia(campanha.getDataFimVigencia());
                Optional<Time> timeOptional = timeRepository.findById(campanha.getTimeId());
                if(timeOptional.isPresent())
                    campanhaUpdate.setTime(timeOptional.get());
                campanhaRepository.save(campanhaUpdate);
                campanhaRepository.flush();

            }
        }catch (Exception e){
            log.error("[ERRO]", e);
        }
    }

    public void deleteCampanhaById(Long id){
        campanhaRepository.deleteById(id);
    }

    public List<CampanhaData> findByTime(Time time){
        return campanhaRepository.findByTimeAndDataFimVigenciaAfter(time, LocalDate.now())
                .stream()
                .map(c -> buildCampanhaData(c))
                .collect(Collectors.toList());
    }

    private CampanhaData buildCampanhaData(Campanha campanha){
        return CampanhaData.builder()
                .id(campanha.getCampanhaId())
                .nomeCampanha(campanha.getNome())
                .dataFimVigencia(campanha.getDataFimVigencia())
                .dataIniVigencia(campanha.getDataIniVigencia())
                .nomeTime(campanha.getTime().getNome())
                .timeId(campanha.getTime().getTimeId())
                .build();
    }

    private void verificaAjustaPeriodo(CampanhaData campanha){
        List<Campanha> campanhasAtivasMesmoPeriodo = campanhaRepository
                .findByDataFimVigenciaGreaterThanOrderByDataFimVigenciaAsc(LocalDate.now());
        campanhasAtivasMesmoPeriodo.stream().forEach(c -> {
            if(c.getDataFimVigencia().compareTo(campanha.getDataFimVigencia()) == 0){
                campanha.setDataFimVigencia(campanha.getDataFimVigencia().plusDays(1));
            }
        });
    }

    private Campanha buildCampanha(CampanhaData campanhaData){
        Campanha campanha = new Campanha();
        campanha.setDataFimVigencia(campanhaData.getDataFimVigencia());
        campanha.setDataIniVigencia(campanhaData.getDataIniVigencia());
        campanha.setNome(campanhaData.getNomeCampanha());
        Time t = new Time();
        t.setTimeId(campanhaData.getTimeId());
        campanha.setTime(t);
        return campanha;
    }
}
