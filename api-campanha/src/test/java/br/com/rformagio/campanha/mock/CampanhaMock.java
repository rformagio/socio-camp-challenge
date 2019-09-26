package br.com.rformagio.campanha.mock;

import br.com.rformagio.campanha.data.CampanhaData;
import br.com.rformagio.campanha.domain.Campanha;
import br.com.rformagio.campanha.domain.Time;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CampanhaMock {

    public static List<CampanhaData> getCampanhasData(){
        List<CampanhaData> campanhaDataList = new ArrayList<>();
        CampanhaData cd1 = CampanhaData.builder()
                .nomeCampanha("Campanha 1")
                .dataIniVigencia(LocalDate.now())
                .dataFimVigencia(LocalDate.now().plusDays(60))
                .timeId(1L)
                .build();
        CampanhaData cd2 = CampanhaData.builder()
                .nomeCampanha("Campanha 2")
                .dataIniVigencia(LocalDate.now())
                .dataFimVigencia(LocalDate.now().plusDays(61))
                .timeId(2L)
                .build();

        CampanhaData cd3 = CampanhaData.builder()
                .nomeCampanha("Campanha 3")
                .dataIniVigencia(LocalDate.now())
                .dataFimVigencia(LocalDate.now().plusDays(62))
                .timeId(3L)
                .build();

        CampanhaData cd4 = CampanhaData.builder()
                .nomeCampanha("Campanha Vencida")
                .dataIniVigencia(LocalDate.now().minusDays(60))
                .dataFimVigencia(LocalDate.now().minusDays(30))
                .timeId(3L)
                .build();
        CampanhaData cd5 = CampanhaData.builder()
                .nomeCampanha("Campanha 4")
                .dataIniVigencia(LocalDate.now())
                .dataFimVigencia(LocalDate.now().plusDays(63))
                .timeId(3L)
                .build();

        campanhaDataList.add(cd1);
        campanhaDataList.add(cd2);
        campanhaDataList.add(cd3);
        campanhaDataList.add(cd4);
        campanhaDataList.add(cd5);

        return campanhaDataList;

    }

    public static List<Campanha> getCampanhas(){

        List<Campanha> campanhas = new ArrayList<>();
        Campanha c1 = criaCampanha("Campanha 1", LocalDate.now(), LocalDate.now().plusDays(60),  1L);
        Campanha c2 = criaCampanha("Campanha 2", LocalDate.now(), LocalDate.now().plusDays(61),  2L);
        Campanha c3 = criaCampanha("Campanha 3", LocalDate.now(), LocalDate.now().plusDays(62),  3L);
        Campanha c4 = criaCampanha("Campanha Vencida", LocalDate.now().minusDays(60), LocalDate.now().minusDays(30),3L);
        Campanha c5 = criaCampanha("Campanha 4", LocalDate.now(), LocalDate.now().plusDays(63), 3L);

        campanhas.add(c1);
        campanhas.add(c2);
        campanhas.add(c3);
        campanhas.add(c4);
        campanhas.add(c5);
        return campanhas;
    }

    public static Campanha criaCampanha(String nomeCampanha, LocalDate dtIniVig, LocalDate dtFimVig, Long idTime){
        Campanha c = new Campanha();
        c.setNome(nomeCampanha);
        c.setDataIniVigencia(dtIniVig);
        c.setDataFimVigencia(dtFimVig);
        Time t = new Time();
        t.setTimeId(idTime);
        c.setTime(t);
        return c;
    }

    public static List<CampanhaData> getCampanhasValidas(){
        List<CampanhaData> campanhaDataList = new ArrayList<>();
        CampanhaData cd1 = CampanhaData.builder()
                .nomeCampanha("Campanha 1")
                .dataIniVigencia(LocalDate.now())
                .dataFimVigencia(LocalDate.now().plusDays(60))
                .timeId(1L)
                .build();
        CampanhaData cd2 = CampanhaData.builder()
                .nomeCampanha("Campanha 2")
                .dataIniVigencia(LocalDate.now())
                .dataFimVigencia(LocalDate.now().plusDays(61))
                .timeId(2L)
                .build();

        CampanhaData cd3 = CampanhaData.builder()
                .nomeCampanha("Campanha 3")
                .dataIniVigencia(LocalDate.now())
                .dataFimVigencia(LocalDate.now().plusDays(62))
                .timeId(3L)
                .build();

        CampanhaData cd4 = CampanhaData.builder()
                .nomeCampanha("Campanha 4")
                .dataIniVigencia(LocalDate.now())
                .dataFimVigencia(LocalDate.now().plusDays(63))
                .timeId(3L)
                .build();

        campanhaDataList.add(cd1);
        campanhaDataList.add(cd2);
        campanhaDataList.add(cd3);
        campanhaDataList.add(cd4);
        return campanhaDataList;

    }

    public static CampanhaData getCampanha(){
        return CampanhaData.builder()
                .nomeCampanha("Campanha Nova")
                .dataIniVigencia(LocalDate.now())
                .dataFimVigencia(LocalDate.now().plusDays(60))
                .timeId(1L)
                .build();
    }

}
