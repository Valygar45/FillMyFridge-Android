package fr.utt.if26.fillmyfridge.Objects;

/**
 * Created by valygar on 21/11/2017.
 */

public class ItemListeDeCourse {

    private Ingredient ingredient;
    private int nbFois;

    public ItemListeDeCourse(Ingredient ingredient, int nbFois) {
        this.ingredient = ingredient;
        this.nbFois = nbFois;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getNbFois() {
        return nbFois;
    }

    public void setNbFois(int nbFois) {
        this.nbFois = nbFois;
    }

    @Override
    public String toString(){
        if(this.ingredient.getGrammes() > 0){
            return nbFois * this.ingredient.getGrammes() + "g - " + ingredient.getNom();
        }else{
            return nbFois + " - " + ingredient.getNom();
        }
    }

}
