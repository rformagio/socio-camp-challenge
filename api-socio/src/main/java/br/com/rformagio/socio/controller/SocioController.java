package br.com.rformagio.socio.controller;

import br.com.rformagio.socio.data.CampanhaData;
import br.com.rformagio.socio.data.SocioData;
import br.com.rformagio.socio.exception.BusinessException;
import br.com.rformagio.socio.service.SocioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/socios")
public class SocioController {

    @Autowired
    SocioService socioService;

    @ApiOperation(value = "Cria um socio e associa as campanhas referentes ao seu time. Caso o socio já exista, apenas atualiza sua lista de campanhas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso."),
            @ApiResponse(code = 302, message = "Usuário já cadastrado.")
    }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CampanhaData>> addSocioCampanhas(@RequestBody SocioData socioData){
        List<CampanhaData> campanhaDataList;
        HttpStatus httpStatus;
        String message = "";
        HttpHeaders httpHeaders = new HttpHeaders();
        try{
            campanhaDataList = socioService.createSocio(socioData);
            httpStatus = HttpStatus.OK;
            message = "Sucesso";
            httpHeaders.set("API-MESSAGE", message);
        }catch (BusinessException be){
            campanhaDataList = socioService.addNovasCampanhas(socioData);
            httpStatus = HttpStatus.FOUND;
            message = "Usuário já cadastrado!";
            httpHeaders.set("API-MESSAGE", message);
        }

        return ResponseEntity.status(httpStatus)
                .headers(httpHeaders)
                .body(campanhaDataList);

    }
}
