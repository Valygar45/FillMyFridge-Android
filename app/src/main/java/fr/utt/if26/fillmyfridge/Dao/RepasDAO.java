package fr.utt.if26.fillmyfridge.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fr.utt.if26.fillmyfridge.Objects.Plat;
import fr.utt.if26.fillmyfridge.Objects.Repas;

/**
 * Created by alex2 on 15/11/2017.
 */

public class RepasDAO {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private String[] allColumns = {"id",
            "nom"};

    public RepasDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Repas createRepas(String nom, ArrayList<Plat> plats, int numberofPersonnes, int numero) {
        ContentValues values = new ContentValues();
        values.put("Nom", nom);
        long insertId = database.insert("Repas", null,
                values);
        Cursor cursor = database.query("Repas",
                allColumns, "id" + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Repas newRepas = cursorToRepas(cursor);
        cursor.close();
        return newRepas;
    }

    private Repas cursorToRepas(Cursor cursor) {
        int id = cursor.getInt(0);
        String nom = cursor.getString(1);
        Repas repas = new Repas(id, nom, null, 0, 0);

        return repas;
    }
}
