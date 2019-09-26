package br.com.rformagio.campanha.controller;

import br.com.rformagio.campanha.data.CampanhaData;
import br.com.rformagio.campanha.data.SocioCampanhaData;
import br.com.rformagio.campanha.service.SocioCampanhaService;
import br.com.rformagio.campanha.util.ValidateInputData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sociocampanhas")
public class SocioCampanhaController {

    @Autowired
    SocioCampanhaService socioCampanhaService;

    @Autowired
    ValidateInputData validateInputData;

    @ApiOperation(value = "Cria um socio e associa as campanhas referentes ao seu time. Caso o socio já exista, apenas atualiza sua lista de campanhas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso."),
    }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CampanhaData> findByIdSocioCampanha(@RequestBody SocioCampanhaData socioCampanhaData){
        List<CampanhaData> list;
        list = socioCampanhaService.addSocioCampanha(socioCampanhaData.getIdSocio(),
                socioCampanhaData.getTimeId());
        return list;
    }

}
