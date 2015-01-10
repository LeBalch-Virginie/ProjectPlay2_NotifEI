package models;

import javax.persistence.Entity;

/**
 * Created by wizard on 10/01/2015.
 */
@Entity
public class hierarchie_cl_ch {

    private int pere;
    private int fils;

    public int getPere() {
        return pere;
    }

    public void setPere(int pere) {
        this.pere = pere;
    }

    public int getFils() {
        return fils;
    }

    public void setFils(int fils) {
        this.fils = fils;
    }
}
