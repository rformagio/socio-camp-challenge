package br.com.rformagio.campanha.util;

import br.com.rformagio.campanha.data.CampanhaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidateInputData {

    @Autowired
    Validator validator;

    public Long validateInputId(String id){

        if(StringUtils.isEmpty(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ID é obrigatório");
        } else if(!id.matches("[0-9]+")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O ID deve ser um número");
        }

        Long ret;

        try{
            ret = Long.valueOf(id);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ops...",
                    e);
        }
        return ret;
    }

    public void validateCampanha(CampanhaData campanhaData){
        StringBuilder errors = new StringBuilder();
        Set<ConstraintViolation<CampanhaData>> violations = validator.validate(campanhaData);
        if(!violations.isEmpty()) {
            for (ConstraintViolation<CampanhaData> violation : violations) {
                errors.append(violation.getMessage()).append(" | \n");
            }
            if(campanhaData.getDataIniVigencia().compareTo(campanhaData.getDataFimVigencia()) > 0){
                errors.append("Data início deve ser menor que data fim!");
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    errors.toString());
        }

    }

}
