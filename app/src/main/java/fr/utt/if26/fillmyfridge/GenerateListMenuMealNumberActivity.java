package fr.utt.if26.fillmyfridge;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.mindorks.placeholderview.PlaceHolderView;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;

import fr.utt.if26.fillmyfridge.Adapters.MealNumberAdapter;
import fr.utt.if26.fillmyfridge.Objects.ListeMenus;
import fr.utt.if26.fillmyfridge.Objects.Menu;
import fr.utt.if26.fillmyfridge.Objects.Repas;

public class GenerateListMenuMealNumberActivity extends AppCompatActivity implements View.OnClickListener {

    private Button continueButton;
    private ListView lvListeMenu;
    private ListeMenus listeMenus;

    private PlaceHolderView mDrawerView;
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private PlaceHolderView mGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        listeMenus = (ListeMenus) intent.getSerializableExtra("listeMenus");
        listeMenus.setMenus(new ArrayList<Menu>());
        Log.e("serializable", listeMenus.toString());

        for (LocalDate date = new LocalDate(listeMenus.getDateDebut()); date.isBefore(new LocalDate(listeMenus.getDateFin()).plusDays(1)); date = date.plusDays(1))
        {
            listeMenus.getMenus().add(new Menu(date.toString(), date.toDate()));
        }
        Log.v("", listeMenus.toString());

        setContentView(R.layout.activity_generate_list_menu_meal_number);

        continueButton = (Button) findViewById(R.id.gen_menu_list_meal_number_continue);
        continueButton.setOnClickListener(this);

        lvListeMenu = (ListView) findViewById(R.id.LV_gen_meal_number);

        MealNumberAdapter mealNumberAdapter = new MealNumberAdapter(listeMenus, getApplicationContext());

        lvListeMenu.setAdapter(mealNumberAdapter);
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
            case R.id.gen_menu_list_meal_number_continue:
                Intent PPMIntent = new Intent(GenerateListMenuMealNumberActivity.this,
                        GenerateListMenuPersonPerMealActivity.class);

                ArrayList<Menu> menus = new ArrayList<Menu>();
                for(int i = 0; i < lvListeMenu.getCount(); i++){
                    menus.add((Menu) lvListeMenu.getAdapter().getItem(i));
                }
                listeMenus.setMenus(menus);
                PPMIntent.putExtra("listeMenus", listeMenus);
                GenerateListMenuMealNumberActivity.this.startActivity(PPMIntent);
                break;
        }
    }

}
