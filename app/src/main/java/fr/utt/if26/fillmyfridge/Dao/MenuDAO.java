package fr.utt.if26.fillmyfridge.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

import fr.utt.if26.fillmyfridge.Objects.Menu;
import fr.utt.if26.fillmyfridge.Objects.Repas;

/**
 * Created by alex2 on 15/11/2017.
 */

public class MenuDAO {

    private SQLiteDatabase database;
    private int listMenuID;

    public MenuDAO(SQLiteDatabase database, int listMenuID) {
        this.database = database;
        this.listMenuID = listMenuID;
    }

    public void createMenu(Menu menu){
        ContentValues values = new ContentValues();
        values.put("nom", menu.getNom());
        values.put("date", menu.getDate().getTime());

        long insertId = database.insert("Menu", null,
                values);

        RepasDAO repasDao = new RepasDAO(database, (int) insertId);
        for(Repas repas : menu.getRepas()){
            repasDao.createRepas(repas);
        }
        this.addMenusAssociation((int) insertId);
    }

    private void addMenusAssociation(int menuID){
        ContentValues values = new ContentValues();
        values.put("listemenus", this.listMenuID);
        values.put("menu", menuID);

        this.database.insert("ListeMenus_Menu", null,
                values);
    }

    public ArrayList<Menu> getMenus(){
        ArrayList<Menu> menus = new ArrayList<Menu>();

        Cursor cursor = database.rawQuery("SELECT m.id, m.nom, m.date from Menu as m, ListeMenus_Menu as lm WHERE lm.listemenus = "+this.listMenuID+" AND m.id = lm.menu", null);
        while(cursor.moveToNext()) {
            Menu menu = new Menu(cursor.getString(1), new Date(cursor.getInt(2)));
            int menuId = cursor.getInt(0);
            menu.setId(menuId);

            RepasDAO repasDAO = new RepasDAO(database, menuId);
            menu.setRepas(repasDAO.getRepas());

            menus.add(menu); //add the item
        }

        return menus;
    }


}
