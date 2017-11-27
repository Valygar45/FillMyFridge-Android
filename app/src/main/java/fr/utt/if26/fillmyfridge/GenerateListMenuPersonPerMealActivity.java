package fr.utt.if26.fillmyfridge;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mindorks.placeholderview.PlaceHolderView;

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

    private PlaceHolderView mDrawerView;
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private PlaceHolderView mGalleryView;

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
        mDrawer = (DrawerLayout)findViewById(R.id.drawerLayout);
        mDrawerView = (PlaceHolderView)findViewById(R.id.drawerView);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);

        setupDrawer();

    }
    private void setupDrawer(){
        mDrawerView
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_GENERER_MENUS))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_VOIR_LISTES_MENUS))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_A_PROPOS));

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.open_drawer, R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };


        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
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
