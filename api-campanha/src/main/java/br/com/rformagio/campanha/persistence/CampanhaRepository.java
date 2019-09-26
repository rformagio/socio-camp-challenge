package br.com.rformagio.campanha.persistence;

import br.com.rformagio.campanha.domain.Campanha;
import br.com.rformagio.campanha.domain.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long> {

    List<Campanha> findByDataFimVigenciaGreaterThanEqual(LocalDate data);

    List<Campanha> findByDataFimVigenciaGreaterThanOrderByDataFimVigenciaAsc(LocalDate data);

    List<Campanha> findByTimeAndDataFimVigenciaAfter(Time time, LocalDate dt);

    List<Campanha> findByDataFimVigenciaBefore(LocalDate data);

}
