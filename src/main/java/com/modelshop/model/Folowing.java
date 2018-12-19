package com.modelshop.model;

public class Folowing {
    private User folow;
    private User idol;

    public Folowing(User fol, User idol){
        this.folow=fol;
        this.idol=idol;
    }

    public User getFolow() {
        return folow;
    }

    public User getIdol() {
        return idol;
    }

    public void setFolow(User folow) {
        this.folow = folow;
    }

    public void setIdol(User idol) {
        this.idol = idol;
    }
}
