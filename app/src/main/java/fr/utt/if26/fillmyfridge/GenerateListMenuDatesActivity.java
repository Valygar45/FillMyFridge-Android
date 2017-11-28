package fr.utt.if26.fillmyfridge;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;

import com.mindorks.placeholderview.PlaceHolderView;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fr.utt.if26.fillmyfridge.Objects.ListeMenus;

public class GenerateListMenuDatesActivity extends AppCompatActivity implements View.OnClickListener {

    private Button continueButton;
    private EditText dateDebutView;
    private EditText dateFinView;
    private Date dateDebut;
    private Date dateFin;

    private PlaceHolderView mDrawerView;
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private PlaceHolderView mGalleryView;
    private int day, year, month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_list_menu_dates);

        continueButton = (Button) findViewById(R.id.gen_menu_list_dates_continue);
        continueButton.setOnClickListener(this);


        dateDebutView = (EditText) findViewById(R.id.gen_menu_list_from);
        dateFinView = (EditText) findViewById(R.id.gen_menu_list_to);

        dateDebutView.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence cs, int i, int i1, int i2){

            }

            public void onTextChanged(CharSequence cs, int i, int i1, int i2){

            }

            public void afterTextChanged(Editable s) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                try{
                    dateDebut = sdf.parse(s.toString());

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        dateDebutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(0);
            }
        });



        dateFinView.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence cs, int i, int i1, int i2){

            }

            public void onTextChanged(CharSequence cs, int i, int i1, int i2){

            }

            public void afterTextChanged(Editable s) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                try{
                    dateFin = sdf.parse(s.toString());

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        dateFinView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
            }
        });

        mDrawer = (DrawerLayout)findViewById(R.id.drawerLayout);
        mDrawerView = (PlaceHolderView)findViewById(R.id.drawerView);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setupDrawer();

    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        LocalDateTime now = LocalDateTime.now();
        if(id ==0){
            return new DatePickerDialog(this, datePickerListenerDebut, now.getYear(), now.getMonthOfYear()-1, now.getDayOfMonth() );
        }else{
            return new DatePickerDialog(this, datePickerListenerFin, now.getYear(), now.getMonthOfYear()-1, now.getDayOfMonth() );
        }
    }

    private DatePickerDialog.OnDateSetListener datePickerListenerDebut = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            day = selectedDay;
            month = selectedMonth;
            year = selectedYear;
            dateDebutView.setText(selectedDay + "/" + (selectedMonth + 1) + "/"
                    + selectedYear);
        }
    };

    private DatePickerDialog.OnDateSetListener datePickerListenerFin = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            day = selectedDay;
            month = selectedMonth;
            year = selectedYear;
            dateFinView.setText(selectedDay + "/" + (selectedMonth + 1) + "/"
                    + selectedYear);
        }
    };

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
