package br.com.rformagio.campanha.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Time {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long timeId;

    private String nome;
}
