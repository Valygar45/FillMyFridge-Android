package fr.utt.if26.fillmyfridge.Objects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by alex2 on 15/11/2017.
 */

public class Repas implements Serializable {
    private int id;
    private String nom;
    private ArrayList<Plat> plats;
    private int numberofPersonnes;
    private int numero;

    public Repas(int id, String nom, ArrayList<Plat> plats, int numberofPersonnes, int numero) {
        this.id = id;
        this.nom = nom;
        this.plats = plats;
        this.numberofPersonnes = numberofPersonnes;
        this.numero = numero;
    }

    public Repas(String nom, ArrayList<Plat> plats, int numberofPersonnes, int numero) {
        this.nom = nom;
        this.plats = plats;
        this.numberofPersonnes = numberofPersonnes;
        this.numero = numero;
    }

    public Repas(String nom, int numberofPersonnes, int numero) {
        this.nom = nom;
        this.numberofPersonnes = numberofPersonnes;
        this.numero = numero;
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

    public ArrayList<Plat> getPlats() {
        return plats;
    }

    public void setPlats(ArrayList<Plat> plats) {
        this.plats = plats;
    }

    public int getNumberofPersonnes() {
        return numberofPersonnes;
    }

    public void setNumberofPersonnes(int numberofPersonnes) {
        this.numberofPersonnes = numberofPersonnes;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Repas{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", plats=" + plats +
                ", numberofPersonnes=" + numberofPersonnes +
                ", numero=" + numero +
                '}';
    }
}
