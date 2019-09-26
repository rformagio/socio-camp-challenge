package br.com.rformagio.socio.data;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Builder
public class SocioData {
    private Long idSocio;
    @NotEmpty
    private String nomeSocio;
    @Email
    private String email;
    private LocalDate dataNascimento;
    @NotEmpty
    private Long timeId;
}
