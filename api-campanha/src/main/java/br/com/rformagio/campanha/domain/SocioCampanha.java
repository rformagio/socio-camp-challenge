package br.com.rformagio.campanha.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class SocioCampanha implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long socioCampanhaId;

    private Long campanhaId;

    private Long socioId;
}
