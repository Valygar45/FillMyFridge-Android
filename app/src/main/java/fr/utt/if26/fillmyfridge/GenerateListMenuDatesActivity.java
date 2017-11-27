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
import android.widget.CalendarView;

import com.mindorks.placeholderview.PlaceHolderView;

import org.joda.time.LocalDate;

import java.util.Calendar;
import java.util.Date;

import fr.utt.if26.fillmyfridge.Objects.ListeMenus;

public class GenerateListMenuDatesActivity extends AppCompatActivity implements View.OnClickListener {

    private Button continueButton;
    private CalendarView dateDebutView;
    private CalendarView dateFinView;
    private Date dateDebut;
    private Date dateFin;

    private PlaceHolderView mDrawerView;
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private PlaceHolderView mGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_list_menu_dates);

        continueButton = (Button) findViewById(R.id.gen_menu_list_dates_continue);
        continueButton.setOnClickListener(this);



        dateDebutView = (CalendarView) findViewById(R.id.gen_menu_list_from);
        dateFinView = (CalendarView) findViewById(R.id.gen_menu_list_to);
        dateDebut = new Date(dateDebutView.getDate());
        dateFin = new Date(dateFinView.getDate());

        Log.e("dateTest", (new Date(dateDebutView.getDate())).toString());

        dateDebutView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                Calendar c = Calendar.getInstance();
                c.set(i, i1, i2, 0, 0);
                dateDebut = c.getTime();
                //dateDebut = (new LocalDate(i, i1+1, i2)).toDate();
                Log.e("dateTest", dateDebut.getTime()+"");
            }
        });

        dateFinView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                dateFin = (new LocalDate(i, i1+1, i2)).toDate();

            }
        });

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
            case R.id.gen_menu_list_dates_continue:

                ListeMenus listeMenus = new ListeMenus(this.dateDebut, this.dateFin);

                Intent mealNumberIntent = new Intent(GenerateListMenuDatesActivity.this,
                        GenerateListMenuMealNumberActivity.class);
                mealNumberIntent.putExtra("listeMenus", listeMenus);
                GenerateListMenuDatesActivity.this.startActivity(mealNumberIntent);
                break;
        }
    }
}
