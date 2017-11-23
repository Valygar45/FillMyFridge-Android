package fr.utt.if26.fillmyfridge;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import fr.utt.if26.fillmyfridge.Dao.ListeMenusDAO;
import fr.utt.if26.fillmyfridge.Dao.MySQLiteHelper;
import fr.utt.if26.fillmyfridge.Dao.PlatDAO;
import fr.utt.if26.fillmyfridge.Objects.Ingredient;
import fr.utt.if26.fillmyfridge.Objects.ListeMenus;
import fr.utt.if26.fillmyfridge.Objects.Menu;
import fr.utt.if26.fillmyfridge.Objects.Plat;
import fr.utt.if26.fillmyfridge.Objects.Repas;
import fr.utt.if26.fillmyfridge.Objects.Tag;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button generateButton;
    private Button listListeMenusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JodaTimeAndroid.init(this);
        setContentView(R.layout.activity_main);

        generateButton = (Button) findViewById(R.id.home_gen_menu_list);
        generateButton.setOnClickListener(this);

        listListeMenusButton = (Button) findViewById(R.id.home_menu_list);
        listListeMenusButton.setOnClickListener(this);

        /*SQLiteDatabase db = new MySQLiteHelper(this).getWritableDatabase();
        Log.v("lol","lol");

        Ingredient ingredient = new Ingredient(1, "pate", 200);
        ArrayList<Ingredient> arrayIngredient = new ArrayList<Ingredient>();
        arrayIngredient.add(ingredient);
        Tag tag = new Tag(1, "gourmand");
        ArrayList<Tag> arrayTag = new ArrayList<Tag>();
        arrayTag.add(tag);

        Plat plat = new Plat(1, "Pizza", arrayIngredient, arrayTag);
        ArrayList<Plat> arrayPlat = new ArrayList<Plat>();
        arrayPlat.add(plat);

        Repas repas = new Repas("Dejeuner gourmand", arrayPlat, 2, 1);
        ArrayList<Repas> arrayRepas = new ArrayList<Repas>();
        arrayRepas.add(repas);

        Menu menu = new Menu("Lundi", new Date(), arrayRepas);
        Menu menu2 = new Menu("Mardi", new Date(), arrayRepas);
        ArrayList<Menu> arrayMenu = new ArrayList<Menu>();
        arrayMenu.add(menu);
        arrayMenu.add(menu2);

        ListeMenus listeMenus = new ListeMenus(new Date(), new Date(), arrayMenu);

        ListeMenusDAO listeMenusDAO = new ListeMenusDAO(this);
        listeMenusDAO.open();
        listeMenusDAO.createListeMenus(listeMenus);
        listeMenusDAO.close();


        ArrayList<Plat> platsFound = PlatDAO.findPlatByTags(arrayTag, this);
        Log.v("platsFound",platsFound.toString());


        Date today = new Date();
        ListeMenus listeMenusTest = new ListeMenus(today, new Date(today.getTime() + (2*1000 * 60 * 60 * 24)));

        int days = Days.daysBetween(new LocalDate(listeMenusTest.getDateDebut()), new LocalDate(listeMenusTest.getDateFin())).getDays();

        ArrayList<Menu> arrayMenuTest = new ArrayList<Menu>();
        for (LocalDate date = new LocalDate(listeMenusTest.getDateDebut()); date.isBefore(new LocalDate(listeMenusTest.getDateFin())); date = date.plusDays(1))
        {
            Menu menuTest = new Menu(date.toString(), date.toDate());
            arrayMenuTest.add(menuTest);
        }
        listeMenusTest.setMenus(arrayMenuTest);

        Log.v("menus",arrayMenuTest.toString());

        int nbRepas =2;
        for(Menu menuTest : arrayMenuTest){
            ArrayList<Repas> arrayRepasTest = new ArrayList<Repas>();
            for(int i=0;i< nbRepas ; i++){
                int nbPersonnes = 2;
                ArrayList<Plat> platsFoundTest = PlatDAO.findPlatByTags(arrayTag, this);
                Random randomizer = new Random();
                ArrayList<Plat> selectedPlat = new ArrayList<Plat>();
                selectedPlat.add(platsFoundTest.get(randomizer.nextInt(platsFoundTest.size())));
                Repas repasTest = new Repas(menuTest.getDate().toString(),selectedPlat, nbPersonnes,i);
                arrayRepasTest.add(repasTest);
            }
            menuTest.setRepas(arrayRepasTest);
        }

        ListeMenusDAO listeMenusDAO2 = new ListeMenusDAO(this);
        listeMenusDAO2.open();
        listeMenusDAO2.createListeMenus(listeMenusTest);

        //ListeMenus listeRecup = listeMenusDAO.getListeMenus(2);
        //Log.v("listemenus",listeRecup.toString());
        listeMenusDAO.close();

        */




    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.home_gen_menu_list:
                Intent datesIntent = new Intent(MainActivity.this, GenerateListMenuDatesActivity.class);
                MainActivity.this.startActivity(datesIntent);
                break;
            case R.id.home_menu_list:
                Intent listeMenusIntent = new Intent(MainActivity.this, AllListMenuActivity.class);
                MainActivity.this.startActivity(listeMenusIntent);
                //Intent ListeMenusIntent = new Intent(MainActivity.this, ListeDeCourseActivity.class);
                //MainActivity.this.startActivity(listListeMenusIntent);
                break;
        }
    }
}
