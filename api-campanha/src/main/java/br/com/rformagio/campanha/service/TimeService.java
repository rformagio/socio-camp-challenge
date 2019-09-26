package br.com.rformagio.campanha.service;

import br.com.rformagio.campanha.data.TimeData;
import br.com.rformagio.campanha.domain.Time;
import br.com.rformagio.campanha.persistence.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TimeService {

    @Autowired
    TimeRepository timeRepository;

    public List<TimeData> findAll(){
        return timeRepository.findAll()
                .stream()
                .map(t -> buildTimeData(t))
                .collect(Collectors.toList());
    }

    public TimeData findById(Long id){
        TimeData timeData = null;
        Optional<Time> time = timeRepository.findById(id);
        if(time.isPresent()){
            timeData = buildTimeData(time.get());
        }
        return timeData;
    }

    private TimeData buildTimeData(Time t){
        return TimeData.builder()
                .id(t.getTimeId())
                .nome(t.getNome())
                .build();
    }
}
