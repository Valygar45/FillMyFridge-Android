package fr.utt.if26.fillmyfridge.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by alex2 on 15/11/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fillmyfridge.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "BEGIN TRANSACTION;\n" +
            "CREATE TABLE IF NOT EXISTS `Utilisateur_ListeMenus` (\n" +
            "\t`Utilisateur`\tINTEGER,\n" +
            "\t`ListeMenus`\tINTEGER,\n" +
            "\tFOREIGN KEY(`Utilisateur`) REFERENCES `Utilisateur`,\n" +
            "\tFOREIGN KEY(`ListeMenus`) REFERENCES `ListeMenus`,\n" +
            "\tPRIMARY KEY(`Utilisateur`,`ListeMenus`)\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS `Utilisateur` (\n" +
            "\t`ID`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`Nom`\tTEXT\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS `Tag` (\n" +
            "\t`ID`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`Label`\tTEXT NOT NULL\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS `Repas_Plat` (\n" +
            "\t`repas`\tINTEGER,\n" +
            "\t`plat`\tINTEGER,\n" +
            "\tFOREIGN KEY(`repas`) REFERENCES `Repas`,\n" +
            "\tPRIMARY KEY(`plat`,`repas`),\n" +
            "\tFOREIGN KEY(`plat`) REFERENCES `Plat`\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS `Repas` (\n" +
            "\t`ID`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`Nom`\tTEXT\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS `Plat_Tag` (\n" +
            "\t`plat`\tINTEGER,\n" +
            "\t`tag`\tINTEGER,\n" +
            "\tFOREIGN KEY(`tag`) REFERENCES `Tag`,\n" +
            "\tFOREIGN KEY(`plat`) REFERENCES `Plat`,\n" +
            "\tPRIMARY KEY(`tag`,`plat`)\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS `Plat_Ingredient` (\n" +
            "\t`plat`\tINTEGER,\n" +
            "\t`ingredient`\tINTEGER,\n" +
            "\t`grammes`\tINTEGER,\n" +
            "\tFOREIGN KEY(`ingredient`) REFERENCES `Ingredient`,\n" +
            "\tPRIMARY KEY(`plat`,`ingredient`),\n" +
            "\tFOREIGN KEY(`plat`) REFERENCES `Plat`\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS `Plat` (\n" +
            "\t`ID`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`intitule`\tTEXT NOT NULL\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS `Menu_Repas` (\n" +
            "\t`menu`\tINTEGER,\n" +
            "\t`repas`\tINTEGER,\n" +
            "\t`numero`\tINTEGER NOT NULL,\n" +
            "\t`personnes`\tINTEGER NOT NULL,\n" +
            "\tFOREIGN KEY(`menu`) REFERENCES `Menu`,\n" +
            "\tPRIMARY KEY(`repas`,`menu`),\n" +
            "\tFOREIGN KEY(`repas`) REFERENCES `Repas`\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS `Menu` (\n" +
            "\t`ID`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`Nom`\tTEXT NOT NULL,\n" +
            "\t`Date`\tINTEGER NOT NULL\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS `ListeMenus_Menu` (\n" +
            "\t`ListeMenus`\tINTEGER,\n" +
            "\t`Menu`\tINTEGER,\n" +
            "\tFOREIGN KEY(`ListeMenus`) REFERENCES `ListeMenus`,\n" +
            "\tFOREIGN KEY(`Menu`) REFERENCES `Menu`,\n" +
            "\tPRIMARY KEY(`ListeMenus`,`Menu`)\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS `ListeMenus` (\n" +
            "\t`ID`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`date_debut`\tINTEGER NOT NULL,\n" +
            "\t`date_fin`\tINTEGER NOT NULL\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS `Ingredient` (\n" +
            "\t`Nom`\tTEXT NOT NULL,\n" +
            "\t`ID`\tINTEGER PRIMARY KEY AUTOINCREMENT\n" +
            ");\n" +
            "INSERT INTO `Ingredient` VALUES ('Test',1);\n" +
            "COMMIT;\n";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        onCreate(db);
    }

}
