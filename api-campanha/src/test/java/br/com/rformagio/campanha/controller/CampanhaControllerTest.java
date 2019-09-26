package br.com.rformagio.campanha.controller;


import br.com.rformagio.campanha.CampanhaApplication;
import br.com.rformagio.campanha.data.CampanhaData;
import br.com.rformagio.campanha.domain.Campanha;
import br.com.rformagio.campanha.mock.TimeMock;
import br.com.rformagio.campanha.persistence.CampanhaRepository;
import br.com.rformagio.campanha.persistence.TimeRepository;
import br.com.rformagio.campanha.service.CampanhaService;
import br.com.rformagio.campanha.mock.CampanhaMock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {CampanhaApplication.class})
public class CampanhaControllerTest {

    @Autowired
    CampanhaController campanhaController;

    @Autowired
    CampanhaService campanhaService;

    @Autowired
    CampanhaRepository campanhaRepository;

    @Autowired
    TimeRepository timeRepository;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void retornaSomenteQuatroCampanhasAtivasDoTotalDeCincoCadastradas() {
        campanhaRepository.deleteAll();
        campanhaRepository.flush();
        timeRepository.saveAll(TimeMock.getTimes());
        timeRepository.flush();
        campanhaRepository.saveAll(CampanhaMock.getCampanhas());
        List<CampanhaData> campanhaDataList = campanhaController.findAll();
        Assert.assertNotNull(campanhaDataList);
        Assert.assertFalse (campanhaDataList.isEmpty());
        Assert.assertEquals(campanhaDataList.size(), 4);
    }

    @Test
    public void criaCampanha() {
        campanhaRepository.deleteAll();
        campanhaRepository.flush();
        timeRepository.saveAll(TimeMock.getTimes());
        timeRepository.flush();
        campanhaRepository.saveAll(CampanhaMock.getCampanhas());
        campanhaController.create(CampanhaMock.getCampanha());
        List<CampanhaData> campanhaDataList = campanhaController.findAll();
        Assert.assertNotNull(campanhaDataList);
        Assert.assertFalse (campanhaDataList.isEmpty());
        Assert.assertEquals(campanhaDataList.size(), 5);
    }

    @Test
    public void updateCampanhaVencida() {
        campanhaRepository.deleteAll();
        campanhaRepository.flush();
        timeRepository.saveAll(TimeMock.getTimes());
        timeRepository.flush();
        campanhaRepository.saveAll(CampanhaMock.getCampanhas());
        campanhaRepository.flush();
        List<Campanha> campanhas = campanhaRepository.findByDataFimVigenciaBefore(LocalDate.now());
        Campanha campanha = campanhas.get(0);
        CampanhaData campanhaData = createCampanhaData(campanha);
        campanhaData.setDataFimVigencia(LocalDate.now().plusDays(70));
        campanhaData.setDataIniVigencia(LocalDate.now().plusDays(1));
        campanhaController.update(campanhaData);
        List<CampanhaData> campanhaDataList = campanhaController.findAll();
        Assert.assertNotNull(campanhaDataList);
        Assert.assertFalse (campanhaDataList.isEmpty());
        Assert.assertEquals(campanhaDataList.size(), 5);
    }

    @Test
    public void deletaCampanha() {
        campanhaRepository.deleteAll();
        campanhaRepository.flush();
        timeRepository.saveAll(TimeMock.getTimes());
        timeRepository.flush();
        campanhaRepository.saveAll(CampanhaMock.getCampanhas());
        List<CampanhaData> campanhaDataList = campanhaController.findAll();
        CampanhaData campanhaData = campanhaDataList.get(0);
        Long id = campanhaData.getId();
        campanhaController.delete(String.valueOf(id));
        try {
            campanhaData = campanhaController.findById(String.valueOf(id));
        }catch(ResponseStatusException ree){
            Assert.assertEquals(ree.getStatus(), HttpStatus.NOT_FOUND);
        }
    }

    private CampanhaData createCampanhaData(Campanha campanha){
        return CampanhaData.builder()
                .dataFimVigencia(campanha.getDataFimVigencia())
                .dataIniVigencia(campanha.getDataIniVigencia())
                .id(campanha.getCampanhaId())
                .nomeCampanha(campanha.getNome())
                .timeId(campanha.getTime().getTimeId())
                .nomeTime(campanha.getTime().getNome())
                .build();
    }

}


