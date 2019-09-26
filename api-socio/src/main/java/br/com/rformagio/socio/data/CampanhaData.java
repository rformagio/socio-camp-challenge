package br.com.rformagio.socio.data;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
public class CampanhaData {
    private Long id;
    @NotEmpty(message = "O nome da campanha é obrigatório.")
    private String nomeCampanha;
    @FutureOrPresent(message = "Data de início da campanha deve ser maior ou igual a data atual.")
    private LocalDate dataIniVigencia;
    @Future(message = "Data de fim da campanha de ser maior que a data atual.")
    private LocalDate dataFimVigencia;
    @NotNull(message = "O time é obrigatório.")
    private Long timeId;
    private String nomeTime;
}
