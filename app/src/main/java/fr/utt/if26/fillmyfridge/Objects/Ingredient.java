package fr.utt.if26.fillmyfridge.Objects;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by alex2 on 15/11/2017.
 */

public class Ingredient implements Serializable {
    private int id;
    private String nom;
    private int grammes;

    public Ingredient(int id, String nom, int grammes) {
        this.id = id;
        this.nom = nom;
        this.grammes = grammes;
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

    public int getGrammes() {
        return grammes;
    }

    public void setGrammes(int grammes) {
        this.grammes = grammes;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", grammes=" + grammes +
                '}';
    }

    @Override
    public boolean equals(Object i){
        //Log.e("LISTMENUEquals", this.getNom());
        //Log.e("LISTMENUEquals", ((Ingredient)i).getNom());
        if(this.getNom().equals(((Ingredient)i).getNom())){
            return true;
        }else{
            return false;
        }
    }
}
