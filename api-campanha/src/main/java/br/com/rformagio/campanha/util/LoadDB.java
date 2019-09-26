package br.com.rformagio.campanha.util;

import br.com.rformagio.campanha.domain.Campanha;
import br.com.rformagio.campanha.domain.Time;
import br.com.rformagio.campanha.persistence.CampanhaRepository;
import br.com.rformagio.campanha.persistence.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class LoadDB implements ApplicationRunner {

    @Autowired
    TimeRepository timeRepository;

    @Autowired
    CampanhaRepository campanhaRepository;

    @Value("${timesCoracao}")
    private String timesCoracao;

    @Override
    public void run(ApplicationArguments args){
        List<Time> times = timeRepository.findAll();
        long count = 1;
        if(times.isEmpty()){
            List<String> aux = Arrays.asList(timesCoracao.split(","));
            for(String s : aux){
                Time t = new Time();
                t.setNome(s);
                t.setTimeId(count);
                count++;
                times.add(t);
            }
            timeRepository.saveAll(times);
        }

        List<Time> ts = timeRepository.findAll();

        Campanha c1 = new Campanha();
        c1.setNome("Campanha 1");
        c1.setDataIniVigencia(LocalDate.now().plusDays(1));
        c1.setDataFimVigencia(LocalDate.now().plusDays(60));
        c1.setTime(ts.get(1));

        campanhaRepository.save(c1);

        Campanha c2 = new Campanha();
        c2.setNome("Campanha 2");
        c2.setDataIniVigencia(LocalDate.now().plusDays(1));
        c2.setDataFimVigencia(LocalDate.now().plusDays(61));
        c2.setTime(ts.get(2));

        campanhaRepository.save(c2);
    }
}
