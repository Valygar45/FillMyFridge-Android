package fr.utt.if26.fillmyfridge.Objects;

import java.util.ArrayList;

/**
 * Created by alex2 on 15/11/2017.
 */

public class Utilisateur {
    private int id;
    private String nom;
    private ArrayList<ListeMenus> listesMenus;

    public Utilisateur(int id, String nom1, ArrayList<ListeMenus> listesMenus) {
        this.nom = nom;
        this.nom = nom1;
        this.listesMenus = listesMenus;
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

    public ArrayList<ListeMenus> getListesMenus() {
        return listesMenus;
    }

    public void setListesMenus(ArrayList<ListeMenus> listesMenus) {
        this.listesMenus = listesMenus;
    }
}
