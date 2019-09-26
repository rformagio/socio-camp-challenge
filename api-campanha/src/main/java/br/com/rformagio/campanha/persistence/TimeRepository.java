package br.com.rformagio.campanha.persistence;

import br.com.rformagio.campanha.domain.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, Long> {
}
