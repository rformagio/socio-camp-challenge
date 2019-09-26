package br.com.rformagio.campanha.controller;

import br.com.rformagio.campanha.CampanhaApplication;
import br.com.rformagio.campanha.data.TimeData;
import br.com.rformagio.campanha.mock.TimeMock;
import br.com.rformagio.campanha.persistence.TimeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {CampanhaApplication.class})
public class TimeControllerTest {

    @Autowired
    TimeController timeController;

    @Autowired
    TimeRepository timeRepository;

    @Test
    public void retornaTodosOsTimes(){
        timeRepository.saveAll(TimeMock.getTimes());
        timeRepository.flush();
        List<TimeData> timeDataList = timeController.findAll();
        Assert.assertNotNull(timeDataList);
        Assert.assertFalse(timeDataList.isEmpty());
        Assert.assertEquals(timeDataList.size(), 4);
    }

    @Test
    public void retornaUmTimePeloId(){
        timeRepository.saveAll(TimeMock.getTimes());
        timeRepository.flush();
        TimeData timeData = timeController.findById(1L);
        Assert.assertNotNull(timeData);
        Assert.assertEquals(timeData.getId().longValue(), 1);

    }
}
