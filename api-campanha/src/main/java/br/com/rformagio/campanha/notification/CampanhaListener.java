package br.com.rformagio.campanha.notification;

import br.com.rformagio.campanha.domain.Campanha;

import javax.persistence.PostUpdate;

public class CampanhaListener {

    @PostUpdate
    public void onPostUpdate(Object o) {
        Campanha campanha = (Campanha)o;
        System.out.println(" #####################################################################");
        System.out.println(" ");
        System.out.println(" ###############   Campanha alterada: " + campanha.getNome());
        System.out.println(" ");
        System.out.println(" #####################################################################");
    }
}
