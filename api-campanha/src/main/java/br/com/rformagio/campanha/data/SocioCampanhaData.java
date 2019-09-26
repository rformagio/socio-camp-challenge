package br.com.rformagio.campanha.data;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Builder
public class SocioCampanhaData {
    private Long idSocio;
    @NotEmpty(message = "")
    private String nomeSocio;
    @Email
    private String email;
    private LocalDate dataNascimento;
    @NotEmpty
    private Long timeId;
}
