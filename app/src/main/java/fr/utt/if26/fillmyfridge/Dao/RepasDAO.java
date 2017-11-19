package fr.utt.if26.fillmyfridge.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

import fr.utt.if26.fillmyfridge.Objects.Plat;
import fr.utt.if26.fillmyfridge.Objects.Repas;

/**
 * Created by alex2 on 15/11/2017.
 */

public class RepasDAO {
    private SQLiteDatabase database;
    private int menuID;

    public RepasDAO(SQLiteDatabase database, int menuID) {
        this.database = database;
        this.menuID = menuID;
    }

    public void createRepas(Repas repas) {
        ContentValues values = new ContentValues();
        values.put("nom", repas.getNom());
        values.put("personnes", repas.getNumberofPersonnes());
        values.put("numero", repas.getNumero());

        long insertId = database.insert("Repas", null,
                values);

        PlatDAO platDao = new PlatDAO((int) insertId, database);
        for(Plat plat : repas.getPlats()){
            platDao.addPlat(plat);
        }
        this.addRepasAssociation( (int)insertId);



        /*Cursor cursor = database.query("Repas",
                allColumns, "id" + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Repas newRepas = cursorToRepas(cursor);
        cursor.close();
        return newRepas;*/
    }

    private Repas cursorToRepas(Cursor cursor) {
        int id = cursor.getInt(0);
        String nom = cursor.getString(1);
        Repas repas = new Repas(id, nom, null, 0, 0);

        return repas;
    }

    private void addRepasAssociation(int repasID){
        ContentValues values = new ContentValues();
        values.put("menu", this.menuID);
        values.put("repas", repasID);

        this.database.insert("Menu_Repas", null,
                values);
    }

    public ArrayList<Repas> getRepas(){
        ArrayList<Repas> arrayRepas = new ArrayList<Repas>();

        Cursor cursor = database.rawQuery("SELECT r.id, r.nom, r.personnes, r.numero from Repas as r, Menu_Repas as mr WHERE mr.menu = "+this.menuID+" AND r.id = mr.repas", null);
        while(cursor.moveToNext()) {
            Repas repas = new Repas(cursor.getString(1), cursor.getInt(2), cursor.getInt(3));
            int repasId = cursor.getInt(0);
            repas.setId(repasId);

            PlatDAO platDAO = new PlatDAO(repasId, database);
            repas.setPlats(platDAO.getPlats());

            arrayRepas.add(repas); //add the item
        }

        return arrayRepas;
    }
}
