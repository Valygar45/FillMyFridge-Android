package fr.utt.if26.fillmyfridge.Objects;

import java.util.ArrayList;

/**
 * Created by alex2 on 15/11/2017.
 */

public class Plat {
    private int id;
    private String intitule;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Tag> tags;

    public Plat(int id, String intitule, ArrayList<Ingredient> ingredients, ArrayList<Tag> tags) {
        this.id = id;
        this.intitule = intitule;
        this.ingredients = ingredients;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Plat{" +
                "id=" + id +
                ", intitule='" + intitule + '\'' +
                ", ingredients=" + ingredients +
                ", tags=" + tags +
                '}';
    }
}
