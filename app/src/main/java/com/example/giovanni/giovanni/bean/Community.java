package com.example.giovanni.giovanni.bean;

import java.io.Serializable;
import java.util.List;

public class Community implements Serializable {

    private List<Gruppo> gruppi;

    public Community(List<Gruppo> gruppi) {
        this.gruppi = gruppi;
    }

    public List<Gruppo> getGruppi() {
        return gruppi;
    }

    public void setGruppi(List<Gruppo> gruppi) {
        this.gruppi = gruppi;
    }

    public Gruppo getGroupByName(String nomeGruppo) {
        Gruppo gruppo = new Gruppo();
        for (Gruppo temp : gruppi) {
            if (temp.getNome().equalsIgnoreCase(nomeGruppo)) {
                gruppo = temp;
            }
            break;
        }
        return gruppo;
    }
}