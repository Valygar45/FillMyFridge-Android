package fr.utt.if26.fillmyfridge.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

import fr.utt.if26.fillmyfridge.Objects.Ingredient;
import fr.utt.if26.fillmyfridge.Objects.Plat;
import fr.utt.if26.fillmyfridge.Objects.Repas;
import fr.utt.if26.fillmyfridge.Objects.Tag;

/**
 * Created by alex2 on 15/11/2017.
 */

public class PlatDAO {

    private int repasID;
    private SQLiteDatabase database;
    private static ArrayList<Integer> idPlatsAdded;

    public PlatDAO(int repasID, SQLiteDatabase database) {
        this.repasID = repasID;
        this.database = database;
    }

    public void addPlat(Plat plat){
        ContentValues values = new ContentValues();
        values.put("plat", plat.getId());
        values.put("repas", this.repasID);

        database.insert("Repas_Plat", null,
                values);
    }

    public static ArrayList<Plat> findPlatByTags(ArrayList<Tag> tags, Context context){
        SQLiteDatabase database = new MySQLiteHelper(context).getWritableDatabase();
        ArrayList<Plat> plats = new ArrayList<Plat>();

        String selectTag = "";
        String selectPlatId = "";
        for(Tag tag : tags){
            selectTag+= "'"+tag.getId()+"',";
        }
        selectTag = selectTag.substring(0, selectTag.length() - 1);

        if(idPlatsAdded==null){
            idPlatsAdded = new ArrayList<Integer>();
            idPlatsAdded.add(0);
        }
        for(Integer idPlat : idPlatsAdded){
            selectPlatId+= "'"+idPlat+"',";
        }
        selectPlatId = selectPlatId.substring(0, selectPlatId.length() - 1);

        Cursor cursor = database.rawQuery("SELECT DISTINCT p.id, p.intitule from Plat as p, Tag as t, Plat_Tag as pt WHERE t.id IN ("+selectTag+") AND pt.tag = t.id AND pt.plat = p.id AND p.id NOT IN ("+selectPlatId+");", null);
        while(cursor.moveToNext()) {
            plats.add(cursorToPlat(cursor, database)); //add the item
        }
        if(plats.isEmpty()){
            idPlatsAdded = new ArrayList<Integer>();
            idPlatsAdded.add(0);
            selectPlatId= "'"+0+"'";
            Cursor cursor2 = database.rawQuery("SELECT DISTINCT p.id, p.intitule from Plat as p, Tag as t, Plat_Tag as pt WHERE t.id IN ("+selectTag+") AND pt.tag = t.id AND pt.plat = p.id AND p.id NOT IN ("+selectPlatId+");", null);
            while(cursor2.moveToNext()) {
                plats.add(cursorToPlat(cursor2, database)); //add the item
            }
        }

        return plats;
    }

    public static void addPlatAdded(Plat plat){
        if(idPlatsAdded==null){
            idPlatsAdded = new ArrayList<Integer>();
            idPlatsAdded.add(0);
        }
        idPlatsAdded.add(plat.getId());

    }

    private static Plat cursorToPlat(Cursor cursor, SQLiteDatabase database) {
        int id = cursor.getInt(0);
        String nom = cursor.getString(1);
        Log.v("platsFound",nom);

        ArrayList<Tag> tags = new ArrayList<Tag>();
        Cursor cursorTag = database.rawQuery("SELECT DISTINCT t.id, t.label from Tag as t, Plat_Tag as pt WHERE pt.plat = "+id+" AND pt.tag = t.id", null);
        while(cursorTag.moveToNext()) {
            tags.add(new Tag(cursorTag.getInt(0), cursorTag.getString(1))); //add the item
        }

        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        Cursor cursorIngredient = database.rawQuery("SELECT DISTINCT i.id, i.nom, pi.grammes from Ingredient as i, Plat_Ingredient as pi WHERE pi.plat = "+id+" AND pi.ingredient = i.id", null);
        while(cursorIngredient.moveToNext()) {
            ingredients.add(new Ingredient(cursorIngredient.getInt(0), cursorIngredient.getString(1), cursorIngredient.getInt(2))); //add the item
        }

        Plat plat = new Plat(id, nom, ingredients, tags);

        return plat;
    }

    public ArrayList<Plat> getPlats(){
        ArrayList<Plat> plats = new ArrayList<Plat>();
        Cursor cursor = database.rawQuery("SELECT DISTINCT p.id, p.intitule from Plat as p, Repas_Plat as rp WHERE rp.repas = "+this.repasID+" AND p.id = rp.plat;", null);
        while(cursor.moveToNext()) {
            plats.add(cursorToPlat(cursor, database)); //add the item
        }


        return plats;
    }
}
