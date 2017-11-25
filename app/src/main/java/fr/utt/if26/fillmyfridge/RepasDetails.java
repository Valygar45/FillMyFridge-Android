package fr.utt.if26.fillmyfridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.utt.if26.fillmyfridge.Objects.Ingredient;
import fr.utt.if26.fillmyfridge.Objects.Plat;
import fr.utt.if26.fillmyfridge.Objects.Repas;

public class RepasDetails extends AppCompatActivity {

    private Repas repas;
    private Plat plat;
    private TextView txNom;
    private TextView txPersonnes;
    private LinearLayout llIngredient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repas_details);

        Intent intent = getIntent();
        repas = (Repas) intent.getSerializableExtra("repas");
        plat = repas.getPlats().get(0);

        txNom = (TextView) findViewById(R.id.TV_details_repas_nom);
        txPersonnes = (TextView) findViewById(R.id.TV_details_repas_nombre_personnes);
        llIngredient = (LinearLayout) findViewById(R.id.LL_details_repas_ingredients);


        txNom.setText("Nom : "+plat.getIntitule());
        txPersonnes.setText("Nombre de personnes : "+repas.getNumberofPersonnes());

        for(Ingredient ingredient : plat.getIngredients()){
            TextView txIngredient = new TextView(this);
            String stringIngredient = ingredient.getNom()+", ";
            if(ingredient.getGrammes()<0){
                stringIngredient+= ingredient.getGrammes()*(-1)*repas.getNumberofPersonnes();
            }
            else{
                stringIngredient += ingredient.getGrammes()*repas.getNumberofPersonnes()+" grammes";
            }
            txIngredient.setText(stringIngredient);
            llIngredient.addView(txIngredient);
        }


    }
}
