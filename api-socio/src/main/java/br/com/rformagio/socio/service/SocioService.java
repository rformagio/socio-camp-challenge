package br.com.rformagio.socio.service;

import br.com.rformagio.socio.client.CampanhaClient;
import br.com.rformagio.socio.data.CampanhaData;
import br.com.rformagio.socio.data.SocioData;
import br.com.rformagio.socio.domain.Socio;
import br.com.rformagio.socio.exception.BusinessException;
import br.com.rformagio.socio.persistence.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SocioService {

    @Autowired
    SocioRepository socioRepository;

    @Autowired
    CampanhaClient campanhaClient;

    public List<CampanhaData> createSocio(SocioData socioData) throws BusinessException{
        List<CampanhaData> campanhas = new ArrayList<>();
        Socio socio = socioRepository.findByEmail(socioData.getEmail());
        if(socio != null){
            throw new BusinessException();
        }
        socioRepository.save(buildSocio(socioData));

        return addCampanhas(socioData);
    }

    public List<CampanhaData> addNovasCampanhas(SocioData socioData){
        return addCampanhas(socioData);
    }

    private List<CampanhaData> addCampanhas(SocioData socioData){
        List<CampanhaData> campanhas = new ArrayList<>();
        campanhas = campanhaClient.addCampanhas(socioData);
        return campanhas;
    }

    private Socio buildSocio(SocioData socioData){
        Socio socio = new Socio();
        socio.setDataNascimento(socioData.getDataNascimento());
        socio.setEmail(socioData.getEmail());
        socio.setNome(socioData.getNomeSocio());
        socio.setTimeId(socioData.getTimeId());
        return socio;
    }

}
