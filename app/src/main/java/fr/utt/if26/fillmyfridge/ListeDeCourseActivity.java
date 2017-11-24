package fr.utt.if26.fillmyfridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import fr.utt.if26.fillmyfridge.Adapters.ListeDeCourseAdapter;
import fr.utt.if26.fillmyfridge.Objects.Ingredient;
import fr.utt.if26.fillmyfridge.Objects.ListeMenus;
import fr.utt.if26.fillmyfridge.Objects.Menu;
import fr.utt.if26.fillmyfridge.Objects.Plat;
import fr.utt.if26.fillmyfridge.Objects.Repas;
import fr.utt.if26.fillmyfridge.Objects.Tag;

public class ListeDeCourseActivity extends AppCompatActivity {

    private ListView lvIngredients;
    private ListeMenus listeMenus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_de_course);

        Intent intent = getIntent();
        listeMenus = (ListeMenus) intent.getSerializableExtra("listeMenus");
        lvIngredients = (ListView) findViewById(R.id.LV_liste_de_course);
        Ingredient ingredient = new Ingredient(1, "pate", 200);
        Ingredient ingredient2 = new Ingredient(1, "tomate", 100);
        Ingredient ingredient3 = new Ingredient(1, "jambon", 20);
        Ingredient ingredient4 = new Ingredient(1, "poulet", 2);
        Ingredient ingredient5 = new Ingredient(1, "steak", 8);
        Ingredient ingredient6 = new Ingredient(1, "poivre", 300);
        ArrayList<Ingredient> arrayIngredient = new ArrayList<Ingredient>();
        ArrayList<Ingredient> arrayIngredient2 = new ArrayList<Ingredient>();
        arrayIngredient.add(ingredient);
        arrayIngredient.add(ingredient2);
        arrayIngredient.add(ingredient3);
        arrayIngredient.add(ingredient4);


        Tag tag = new Tag(1, "gourmand");
        ArrayList<Tag> arrayTag = new ArrayList<Tag>();
        arrayTag.add(tag);

        Plat plat = new Plat(1, "Pizza", arrayIngredient, arrayTag);
        arrayIngredient2.add(ingredient5);
        arrayIngredient2.add(ingredient6);
        Plat plat2 = new Plat(1, "Poulet curry", arrayIngredient2, arrayTag);

        ArrayList<Plat> arrayPlat = new ArrayList<Plat>();
        arrayPlat.add(plat);
        arrayPlat.add(plat2);

        Repas repas = new Repas("Dejeuner gourmand", arrayPlat, 2, 1);
        ArrayList<Repas> arrayRepas = new ArrayList<Repas>();
        arrayRepas.add(repas);

        Menu menu = new Menu("Lundi", new Date(), arrayRepas);
        Menu menu2 = new Menu("Mardi", new Date(), arrayRepas);
        ArrayList<Menu> arrayMenu = new ArrayList<Menu>();
        arrayMenu.add(menu);
        arrayMenu.add(menu2);

        ListeMenus listeMenus2 = new ListeMenus(new Date(), new Date(), arrayMenu);
        Log.e("LISTMENU", listeMenus.getIngredients().toString());
        Log.e("LISTMENU2", listeMenus2.getIngredients().toString());
        ListeDeCourseAdapter ldcAdapter= new ListeDeCourseAdapter(listeMenus,getApplicationContext());

        lvIngredients.setAdapter(ldcAdapter);


    }
}
