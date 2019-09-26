package br.com.rformagio.campanha.mock;

import br.com.rformagio.campanha.domain.Time;

import java.util.ArrayList;
import java.util.List;

public class TimeMock {

    public static List<Time> getTimes(){
        List<Time> times = new ArrayList<>();

        Time t1 = new Time();
        t1.setTimeId(1L);
        t1.setNome("Corinthians");

        Time t2 = new Time();
        t2.setTimeId(2L);
        t2.setNome("Palmeiras");

        Time t3 = new Time();
        t3.setTimeId(3L);
        t3.setNome("Santos");

        Time t4 = new Time();
        t4.setTimeId(4L);
        t4.setNome("São Paulo");

        times.add(t1);
        times.add(t2);
        times.add(t3);
        times.add(t4);

        return times;
    }
}
