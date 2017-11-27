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

import java.text.SimpleDateFormat;

import fr.utt.if26.fillmyfridge.Adapters.ListeMenuDetailsAdapter;
import fr.utt.if26.fillmyfridge.Adapters.MealNumberAdapter;
import fr.utt.if26.fillmyfridge.Dao.ListeMenusDAO;
import fr.utt.if26.fillmyfridge.Objects.ListeMenus;

public class ListMenuDetailsActivity extends AppCompatActivity {

    private ListView lvListeMenu;
    private ListeMenus listeMenus;

    private PlaceHolderView mDrawerView;
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private PlaceHolderView mGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu_details);

        Intent intent = getIntent();
        listeMenus = (ListeMenus) intent.getSerializableExtra("listeMenus");

        ListeMenusDAO listeMenusDAO = new ListeMenusDAO(this);
        listeMenusDAO.open();

        final ListeMenus listeMenusBDD = listeMenusDAO.getListeMenus(listeMenus.getId());
        Log.e("numberRepas", listeMenusBDD.toString());


        TextView txFrom = (TextView) findViewById(R.id.TV_lm_details_from);
        txFrom.setText(new SimpleDateFormat("dd/MM").format(listeMenus.getDateDebut()));
        TextView txTo = (TextView) findViewById(R.id.TV_lm_details_to);
        txTo.setText(new SimpleDateFormat("dd/MM").format(listeMenus.getDateFin()));

        Button buttonListeCourse = (Button) findViewById(R.id.Button_details_menu_liste_course);

        buttonListeCourse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent listListeMenusIntent = new Intent(ListMenuDetailsActivity.this, ListeDeCourseActivity.class);
                listListeMenusIntent.putExtra("listeMenus", listeMenusBDD);
                ListMenuDetailsActivity.this.startActivity(listListeMenusIntent);
            }
        });

        lvListeMenu = (ListView) findViewById(R.id.LV_listemenus_details);

        ListeMenuDetailsAdapter listeMenuDetailsAdapter = new ListeMenuDetailsAdapter(listeMenusBDD, getApplicationContext());

        lvListeMenu.setAdapter(listeMenuDetailsAdapter);
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
}
