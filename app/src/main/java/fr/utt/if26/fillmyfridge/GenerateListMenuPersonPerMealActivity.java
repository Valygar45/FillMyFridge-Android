package fr.utt.if26.fillmyfridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import fr.utt.if26.fillmyfridge.Adapters.MealNumberAdapter;
import fr.utt.if26.fillmyfridge.Adapters.PersonNumberAdapter;
import fr.utt.if26.fillmyfridge.Objects.ListeMenus;
import fr.utt.if26.fillmyfridge.Objects.Menu;
import fr.utt.if26.fillmyfridge.Objects.Repas;

public class GenerateListMenuPersonPerMealActivity extends AppCompatActivity implements View.OnClickListener {

    private Button continueButton;
    private ListeMenus listeMenus;
    private ListView lvListeMenu;
    private Menu menuToSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_list_menu_person_per_meal);
        Intent intent = getIntent();

        listeMenus = (ListeMenus) intent.getSerializableExtra("listeMenus");

        lvListeMenu = (ListView) findViewById(R.id.LV_gen_person_number);

        boolean missing = false;
        int menuId = -1;
        for(Menu menu: listeMenus.getMenus()){
            menuId++;
            for(Repas repas : menu.getRepas()){
                if(repas.getNumberofPersonnes()==0) {
                    missing = true;

                }
            }
            if(missing){break;}
        }
        if(missing){
            menuToSave = listeMenus.getMenus().get(menuId);

            TextView textView = (TextView) findViewById(R.id.TV_meal_person);
            textView.setText("Precisez le nombre de personnes pour le : "+menuToSave.getDateFourDigits());

            PersonNumberAdapter personNumberAdapter = new PersonNumberAdapter(menuToSave, getApplicationContext());
            lvListeMenu.setAdapter(personNumberAdapter);
        }
        else{
            Intent themeIntent = new Intent(GenerateListMenuPersonPerMealActivity.this, GenerateListMenuMealThemeActivity.class);
            themeIntent.putExtra("listeMenus", listeMenus);
            GenerateListMenuPersonPerMealActivity.this.startActivity(themeIntent);
        }




        continueButton = (Button) findViewById(R.id.gen_menu_list_person_per_meal_continue);
        continueButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.gen_menu_list_person_per_meal_continue:
                Intent PPMIntent = new Intent(GenerateListMenuPersonPerMealActivity.this,
                        GenerateListMenuPersonPerMealActivity.class);

                Log.e("RepasPerson", menuToSave.toString());

                PPMIntent.putExtra("listeMenus", listeMenus);
                GenerateListMenuPersonPerMealActivity.this.startActivity(PPMIntent);
                break;
        }
    }
}
