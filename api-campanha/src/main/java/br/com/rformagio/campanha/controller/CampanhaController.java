package br.com.rformagio.campanha.controller;

import br.com.rformagio.campanha.data.CampanhaData;
import br.com.rformagio.campanha.exception.BusinessException;
import br.com.rformagio.campanha.service.CampanhaService;
import br.com.rformagio.campanha.util.ValidateInputData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/campanhas")
public class CampanhaController {

    @Autowired
    CampanhaService campanhaService;

    @Autowired
    ValidateInputData validateInputData;

    @ApiOperation(value = "Retorna uma lista de campanhas ativas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso."),
            @ApiResponse(code = 204, message = "Lista vazia. Não há campanhas ativas.")
    }
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CampanhaData> findAll(){
        List<CampanhaData> listaCampanhasAtivas = campanhaService.findByDataFimVigenciaGreaterThanEqual(LocalDate.now());
        if(listaCampanhasAtivas.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Não há campanhas ativas.");
        }
        return listaCampanhasAtivas;
    }


    @ApiOperation(value = "Retorna a campanha referente ao id infomado.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso."),
            @ApiResponse(code = 404, message = "Campanha não encontrada.")
    }
    )
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CampanhaData findById(@PathVariable String id){
        Long campanhaId = validateInputData.validateInputId(id);
        CampanhaData campanha = campanhaService.findById(campanhaId);
        if(campanha == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Campanha não encontrada");
        }
        return campanha;
    }


    @ApiOperation(value = "Cria uma nova campanha.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso."),
            @ApiResponse(code = 400, message = "Problema de validação!")
    }
    )
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK, reason = "Campanha criada com sucesso")
    public void create(@RequestBody CampanhaData campanha){
        validateInputData.validateCampanha(campanha);
        try {
            campanhaService.createCampanha(campanha);
        }catch(BusinessException be) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, be.getMessage());
        }
    }

    @ApiOperation(value = "Atualiza uma campanha.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso."),
    }
    )
    @PutMapping
    @ResponseStatus(value = HttpStatus.OK, reason = "Campanha atualizada com sucesso")
    public void update(@RequestBody CampanhaData campanha){
        validateInputData.validateCampanha(campanha);
        campanhaService.updateCampanha(campanha);
    }

    @ApiOperation(value = "Deleta uma campanha.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso."),
    }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Campanha apagada com sucesso.")
    public void delete(@PathVariable String id){
        Long campanhaId = validateInputData.validateInputId(id);
        campanhaService.deleteCampanhaById(campanhaId);
    }
}
