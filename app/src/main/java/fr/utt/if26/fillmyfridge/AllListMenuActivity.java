package fr.utt.if26.fillmyfridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.utt.if26.fillmyfridge.Dao.ListeMenusDAO;
import fr.utt.if26.fillmyfridge.Objects.ListeMenus;

public class AllListMenuActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list_menu);
        listView = (ListView) findViewById(R.id.listview_listListeMenus);

        ListeMenusDAO listeMenusDAO = new ListeMenusDAO(this);
        listeMenusDAO.open();
        ArrayList<ListeMenus> listeMenus = listeMenusDAO.getAllListeMenus();

        final ArrayAdapter<ListeMenus> adapter = new ArrayAdapter<ListeMenus>(this,
                android.R.layout.simple_list_item_1, listeMenus);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                ListeMenus listeMenus = (ListeMenus) listView.getAdapter().getItem(position);
                Intent ListMenuDetailIntent = new Intent(AllListMenuActivity.this,
                        ListMenuDetailsActivity.class);
                ListMenuDetailIntent.putExtra("listeMenus", listeMenus);
                AllListMenuActivity.this.startActivity(ListMenuDetailIntent);
            }
        });

    }

}
