package fr.utt.if26.fillmyfridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import fr.utt.if26.fillmyfridge.Adapters.ListeMenuDetailsAdapter;
import fr.utt.if26.fillmyfridge.Adapters.MealNumberAdapter;
import fr.utt.if26.fillmyfridge.Dao.ListeMenusDAO;
import fr.utt.if26.fillmyfridge.Objects.ListeMenus;

public class ListMenuDetailsActivity extends AppCompatActivity {

    private ListView lvListeMenu;
    private ListeMenus listeMenus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu_details);

        Intent intent = getIntent();
        listeMenus = (ListeMenus) intent.getSerializableExtra("listeMenus");

        ListeMenusDAO listeMenusDAO = new ListeMenusDAO(this);
        listeMenusDAO.open();

        ListeMenus listeMenusBDD = listeMenusDAO.getListeMenus(listeMenus.getId());
        Log.e("numberRepas", listeMenusBDD.toString());


        TextView txFrom = (TextView) findViewById(R.id.TV_lm_details_from);
        txFrom.setText(new SimpleDateFormat("dd/MM").format(listeMenus.getDateDebut()));
        TextView txTo = (TextView) findViewById(R.id.TV_lm_details_to);
        txTo.setText(new SimpleDateFormat("dd/MM").format(listeMenus.getDateFin()));

        lvListeMenu = (ListView) findViewById(R.id.LV_listemenus_details);

        ListeMenuDetailsAdapter listeMenuDetailsAdapter = new ListeMenuDetailsAdapter(listeMenusBDD, getApplicationContext());

        lvListeMenu.setAdapter(listeMenuDetailsAdapter);
    }
}
