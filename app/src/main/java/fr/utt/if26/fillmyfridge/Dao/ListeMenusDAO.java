package fr.utt.if26.fillmyfridge.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

import fr.utt.if26.fillmyfridge.Objects.ListeMenus;
import fr.utt.if26.fillmyfridge.Objects.Menu;

/**
 * Created by alex2 on 15/11/2017.
 */

public class ListeMenusDAO {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public ListeMenusDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createListeMenus(ListeMenus listeMenus) {
        ContentValues values = new ContentValues();
        values.put("date_debut", listeMenus.getDateDebut().getTime() );
        values.put("date_fin", listeMenus.getDateFin().getTime() );

        long insertId = database.insert("ListeMenus", null,
                values);

        MenuDAO menuDao = new MenuDAO(database, (int) insertId);
        for(Menu menu : listeMenus.getMenus()){
            menuDao.createMenu(menu);
        }

    }
    public ListeMenus getListeMenus(int id){
        Cursor cursor = database.rawQuery("SELECT * from ListeMenus WHERE id = "+id+";", null);
        cursor.moveToFirst();
        int debut = cursor.getInt(1);
        int fin = cursor.getInt(2);
        ListeMenus listeMenus = new ListeMenus(new Date(debut), new Date(fin));
        listeMenus.setId(id);

        MenuDAO menuDAO = new MenuDAO(database, id);
        listeMenus.setMenus(menuDAO.getMenus());

        return listeMenus;
    }
}
