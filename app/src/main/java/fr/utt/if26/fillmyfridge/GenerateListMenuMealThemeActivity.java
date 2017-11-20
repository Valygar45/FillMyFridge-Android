package fr.utt.if26.fillmyfridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import fr.utt.if26.fillmyfridge.Adapters.PersonNumberAdapter;
import fr.utt.if26.fillmyfridge.Adapters.ThemeAdapter;
import fr.utt.if26.fillmyfridge.Objects.ListeMenus;
import fr.utt.if26.fillmyfridge.Objects.Menu;
import fr.utt.if26.fillmyfridge.Objects.Repas;

public class GenerateListMenuMealThemeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button continueButton;
    private ListeMenus listeMenus;
    private ListView lvListeMenu;
    private Menu menuToSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_list_menu_meal_theme);

        Intent intent = getIntent();
        listeMenus = (ListeMenus) intent.getSerializableExtra("listeMenus");

        Log.e("Theme", listeMenus.toString());

        lvListeMenu = (ListView) findViewById(R.id.LV_gen_meal_theme);

        boolean missing = false;
        int menuId = -1;
        for(Menu menu: listeMenus.getMenus()){
            menuId++;
            for(Repas repas : menu.getRepas()){
                if(repas.getId()==0) {
                    missing = true;

                }
            }
            if(missing){break;}
        }
        if(missing){
            menuToSave = listeMenus.getMenus().get(menuId);

            ThemeAdapter themeAdapter = new ThemeAdapter(menuToSave, getApplicationContext(), this);
            lvListeMenu.setAdapter(themeAdapter);
        }
        else{
            Intent themeIntent = new Intent(GenerateListMenuMealThemeActivity.this, GenerateListMenuEnding.class);
            themeIntent.putExtra("listeMenus", listeMenus);
            GenerateListMenuMealThemeActivity.this.startActivity(themeIntent);
        }


        continueButton = (Button) findViewById(R.id.gen_menu_list_meal_theme_continue);
        continueButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.gen_menu_list_meal_theme_continue:
                Intent datesIntent = new Intent(GenerateListMenuMealThemeActivity.this, GenerateListMenuMealThemeActivity.class);
                datesIntent.putExtra("listeMenus", listeMenus);
                GenerateListMenuMealThemeActivity.this.startActivity(datesIntent);
                break;
        }
    }
}
