package br.com.rformagio.socio.persistence;

import br.com.rformagio.socio.domain.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepository extends JpaRepository<Socio, Long> {

    Socio findByEmail(String email);
}
