package br.com.rformagio.campanha.controller;

import br.com.rformagio.campanha.data.TimeData;
import br.com.rformagio.campanha.service.TimeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/times")
public class TimeController {

    @Autowired
    TimeService timeService;

    @ApiOperation(value = "Retorna uma lista de times válidos.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso."),
            @ApiResponse(code = 204, message = "Lista vazia. Não há times.")
    }
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TimeData> findAll(){
        List<TimeData> listaTimes = timeService.findAll();
        if(listaTimes.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Não há times.");
        }
        return listaTimes;
    }


    @ApiOperation(value = "Retorna o time referente ao id infomado.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso."),
            @ApiResponse(code = 204, message = "Time não encontrada.")
    }
    )
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TimeData findById(@PathVariable Long id){
        TimeData timeData = timeService.findById(id);
        if(timeData == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Time não encontrado!");
        }
        return timeData;
    }




}
