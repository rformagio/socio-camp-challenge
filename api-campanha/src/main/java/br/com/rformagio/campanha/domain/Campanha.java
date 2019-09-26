package br.com.rformagio.campanha.domain;

import br.com.rformagio.campanha.notification.CampanhaListener;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(CampanhaListener.class)
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long campanhaId;

    private String nome;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dataIniVigencia;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dataFimVigencia;

    @ManyToOne
    @JoinColumn(name = "timeId")
    private Time time;

}
