package fr.utt.if26.fillmyfridge.Objects;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alex2 on 15/11/2017.
 */

public class Menu implements Serializable {
    private int id;
    private String nom;
    private Date date;
    private ArrayList<Repas> repas;

    public Menu(int id, String nom, Date date, ArrayList<Repas> repas) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.repas = repas;
    }

    public Menu(String nom, Date date, ArrayList<Repas> repas) {
        this.nom = nom;
        this.date = date;
        this.repas = repas;
    }

    public Menu(String nom, Date date) {
        this.nom = nom;
        this.date = date;
        this.repas = repas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Repas> getRepas() {
        return repas;
    }

    public void setRepas(ArrayList<Repas> repas) {
        this.repas = repas;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", date=" + date +
                ", repas=" + repas +
                '}';
    }

    public String getDateFourDigits(){
        String fourDigitsDate = new SimpleDateFormat("dd/MM").format(this.getDate());
        return fourDigitsDate;
    }
}
