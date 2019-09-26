package br.com.rformagio.campanha.persistence;

import br.com.rformagio.campanha.domain.SocioCampanha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioCampanhaRepository extends JpaRepository<SocioCampanha, Long> {
}
