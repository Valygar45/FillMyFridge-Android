package fr.utt.if26.fillmyfridge;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.mindorks.placeholderview.PlaceHolderView;

import fr.utt.if26.fillmyfridge.Objects.ListeMenus;

public class GenerateListMenuEnding extends AppCompatActivity implements View.OnClickListener {

    private Button goHomeButton;
    private Button seeListButton;
    private ListeMenus listeMenus;

    private PlaceHolderView mDrawerView;
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private PlaceHolderView mGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_list_menu_ending);

        goHomeButton = (Button) findViewById(R.id.gen_lm_ending_go_home);
        goHomeButton.setOnClickListener(this);

        seeListButton = (Button) findViewById(R.id.gen_lm_ending_liste_course);
        seeListButton.setOnClickListener(this);

        Intent intent = getIntent();

        listeMenus = (ListeMenus) intent.getSerializableExtra("listeMenus");
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
            case R.id.gen_lm_ending_go_home:
                Intent goHomeIntent = new Intent(GenerateListMenuEnding.this, MainActivity.class);
                GenerateListMenuEnding.this.startActivity(goHomeIntent);
                break;
            case R.id.gen_lm_ending_liste_course:
                Intent seeListIntent = new Intent(GenerateListMenuEnding.this, ListeDeCourseActivity.class);
                seeListIntent.putExtra("listeMenus", listeMenus);
                GenerateListMenuEnding.this.startActivity(seeListIntent);
                break;
        }
    }
}
