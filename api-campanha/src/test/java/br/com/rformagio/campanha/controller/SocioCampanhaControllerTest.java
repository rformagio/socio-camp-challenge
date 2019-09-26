package br.com.rformagio.campanha.controller;

import br.com.rformagio.campanha.CampanhaApplication;
import br.com.rformagio.campanha.data.CampanhaData;
import br.com.rformagio.campanha.data.SocioCampanhaData;
import br.com.rformagio.campanha.mock.CampanhaMock;
import br.com.rformagio.campanha.mock.TimeMock;
import br.com.rformagio.campanha.persistence.CampanhaRepository;
import br.com.rformagio.campanha.persistence.TimeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {CampanhaApplication.class})
public class SocioCampanhaControllerTest {

    @Autowired
    SocioCampanhaController socioCampanhaController;

    @Autowired
    CampanhaRepository campanhaRepository;

    @Autowired
    TimeRepository timeRepository;

    @Test
    public void associaSocioERetornaAsCampanhas(){
        campanhaRepository.deleteAll();
        campanhaRepository.flush();
        timeRepository.saveAll(TimeMock.getTimes());
        timeRepository.flush();
        campanhaRepository.saveAll(CampanhaMock.getCampanhas());
        SocioCampanhaData socioCampanhaData = createSocioCampanhaData();
        List<CampanhaData> campanhaDataList = socioCampanhaController.findByIdSocioCampanha(socioCampanhaData);
        Assert.assertNotNull(campanhaDataList);
        Assert.assertFalse(campanhaDataList.isEmpty());
        Assert.assertEquals(campanhaDataList.size(), 2);

    }

    private SocioCampanhaData createSocioCampanhaData(){
        return SocioCampanhaData.builder()
                .dataNascimento(LocalDate.of(2000,1,1))
                .email("teste@teste.com")
                .idSocio(1001L)
                .timeId(3L)
                .build();
    }

}
