package br.com.rformagio.socio.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeData {

    private Long id;
    private String nome;
}
