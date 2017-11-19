package fr.utt.if26.fillmyfridge.Dao;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by alex2 on 15/11/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fillmyfridge.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_UTILISATEUR = "CREATE TABLE IF NOT EXISTS `Utilisateur` (\n" +
            "\t`ID`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`Nom`\tTEXT\n" +
            ");";

    private static final String DATABASE_CREATE_TAG = "CREATE TABLE IF NOT EXISTS `Tag` (\n" +
            "\t`ID`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`Label`\tTEXT NOT NULL\n" +
            ");";

    private static final String DATABASE_CREATE_REPAS = "CREATE TABLE IF NOT EXISTS `Repas` (\n" +
            "\t`ID`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`nom`\tTEXT,\n" +
            "\t`personnes`\tINTEGER NOT NULL,\n" +
            "\t`numero`\tINTEGER NOT NULL\n" +
            ");";

    private static final String DATABASE_CREATE_PLAT = "CREATE TABLE IF NOT EXISTS `Plat` (\n" +
            "\t`ID`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`intitule`\tTEXT NOT NULL\n" +
            ");";

    private static final String DATABASE_CREATE_MENU = "CREATE TABLE IF NOT EXISTS `Menu` (\n" +
            "\t`ID`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`nom`\tTEXT NOT NULL,\n" +
            "\t`date`\tINTEGER NOT NULL\n" +
            ");";

    private static final String DATABASE_CREATE_LISTEMENUS = "CREATE TABLE IF NOT EXISTS `ListeMenus` (\n" +
            "\t`ID`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`date_debut`\tINTEGER NOT NULL,\n" +
            "\t`date_fin`\tINTEGER NOT NULL\n" +
            ");";

    private static final String DATABASE_CREATE_INGREDIENT = "CREATE TABLE IF NOT EXISTS `Ingredient` (\n" +
            "\t`Nom`\tTEXT NOT NULL,\n" +
            "\t`ID`\tINTEGER PRIMARY KEY AUTOINCREMENT\n" +
            ");";

    private static final String DATABASE_CREATE_UTILISATEUR_LISTEMENUS = "CREATE TABLE IF NOT EXISTS `Utilisateur_ListeMenus` (\n" +
            "\t`Utilisateur`\tINTEGER,\n" +
            "\t`ListeMenus`\tINTEGER,\n" +
            "\tFOREIGN KEY(`Utilisateur`) REFERENCES `Utilisateur`,\n" +
            "\tPRIMARY KEY(`Utilisateur`,`ListeMenus`),\n" +
            "\tFOREIGN KEY(`ListeMenus`) REFERENCES `ListeMenus`\n" +
            ");";

    private static final String DATABASE_CREATE_REPAS_PLAT = "CREATE TABLE IF NOT EXISTS `Repas_Plat` (\n" +
            "\t`repas`\tINTEGER,\n" +
            "\t`plat`\tINTEGER,\n" +
            "\tFOREIGN KEY(`plat`) REFERENCES `Plat`,\n" +
            "\tPRIMARY KEY(`plat`,`repas`),\n" +
            "\tFOREIGN KEY(`repas`) REFERENCES `Repas`\n" +
            ");";

    private static final String DATABASE_CREATE_PLAT_TAG = "CREATE TABLE IF NOT EXISTS `Plat_Tag` (\n" +
            "\t`plat`\tINTEGER,\n" +
            "\t`tag`\tINTEGER,\n" +
            "\tFOREIGN KEY(`tag`) REFERENCES `Tag`,\n" +
            "\tFOREIGN KEY(`plat`) REFERENCES `Plat`,\n" +
            "\tPRIMARY KEY(`tag`,`plat`)\n" +
            ");";

    private static final String DATABASE_PLAT_INGREDIENT = "CREATE TABLE IF NOT EXISTS `Plat_Ingredient` (\n" +
            "\t`plat`\tINTEGER,\n" +
            "\t`ingredient`\tINTEGER,\n" +
            "\t`grammes`\tINTEGER,\n" +
            "\tPRIMARY KEY(`plat`,`ingredient`),\n" +
            "\tFOREIGN KEY(`ingredient`) REFERENCES `Ingredient`,\n" +
            "\tFOREIGN KEY(`plat`) REFERENCES `Plat`\n" +
            ");";

    private static final String DATABASE_MENU_REPAS = "CREATE TABLE IF NOT EXISTS `Menu_Repas` (\n" +
            "\t`menu`\tINTEGER,\n" +
            "\t`repas`\tINTEGER,\n" +
            "\tFOREIGN KEY(`repas`) REFERENCES `Repas`,\n" +
            "\tPRIMARY KEY(`repas`,`menu`),\n" +
            "\tFOREIGN KEY(`menu`) REFERENCES `Menu`\n" +
            ");";

    private static final String DATABASE_CREATE_LISTEMENUS_MENU = "CREATE TABLE IF NOT EXISTS `ListeMenus_Menu` (\n" +
            "\t`listemenus`\tINTEGER,\n" +
            "\t`menu`\tINTEGER,\n" +
            "\tPRIMARY KEY(`listemenus`,`menu`),\n" +
            "\tFOREIGN KEY(`listemenus`) REFERENCES `ListeMenus`,\n" +
            "\tFOREIGN KEY(`menu`) REFERENCES `Menu`\n" +
            ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.v("lol","lol");
        try {
            database.execSQL(DATABASE_CREATE_UTILISATEUR);
            database.execSQL(DATABASE_CREATE_TAG);
            database.execSQL(DATABASE_CREATE_REPAS);
            database.execSQL(DATABASE_CREATE_PLAT);
            database.execSQL(DATABASE_CREATE_MENU);
            database.execSQL(DATABASE_CREATE_LISTEMENUS);
            database.execSQL(DATABASE_CREATE_INGREDIENT);
            database.execSQL(DATABASE_CREATE_UTILISATEUR_LISTEMENUS);
            database.execSQL(DATABASE_CREATE_REPAS_PLAT);
            database.execSQL(DATABASE_CREATE_PLAT_TAG);
            database.execSQL(DATABASE_PLAT_INGREDIENT);
            database.execSQL(DATABASE_MENU_REPAS);
            database.execSQL(DATABASE_CREATE_LISTEMENUS_MENU);

            database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Pate',NULL);");
            database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Pizza');");
            database.execSQL("INSERT INTO `Tag`(`ID`,`Label`) VALUES (NULL,'Gourmand');");
            database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (1,1);");
            database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (1,1,200);");

        }
        catch (SQLException e){
            Log.v("SQLITE",e.getMessage().toString());

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        onCreate(db);
    }

}
