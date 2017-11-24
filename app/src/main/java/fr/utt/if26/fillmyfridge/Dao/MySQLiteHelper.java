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

            initTags(database);
            initPlat(database);
            initIngredients(database);
            initPlatsTags(database);
            initPlatsIngredients(database);

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

    public void initIngredients(SQLiteDatabase database){
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Lardons',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Une pâte brisée',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Crème fraîche',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Farine',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Lait',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Oeuf',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Gruyère râpé',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Poivre',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Sel',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Muscade',NULL);");

        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Foie de veau',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Raisin sec',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Porto',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Vin Blanc',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Beurre',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Echalotes',NULL);");

        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Blanc de poulet',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Oignon jaune',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Curry',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Cumin',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Piment',NULL);");

        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Morceaux de dinde',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Huile d olive',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Oignon blanc',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Bouillon de volaille',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Concentré de tomate',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Tomates',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Ail',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Herbe de provence',NULL);");

        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Pâte à lasagnes',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Viande hachée de boeuf',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Saucisse',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Carotte',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Céleri',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Basilic',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Mozzarella',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Béchamel',NULL);");

        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Aubergine',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Courgette',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Poivron',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Thym',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Laurier',NULL);");

        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Riz rond',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Champignons',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Mascarpone',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Parmesan',NULL);");

        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Melon',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Avocat',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Olive Noire',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Tomate cerise',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Sésame',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Citron vert',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Fines herbes',NULL);");

        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Coeur de laitue',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Croûtons',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Capres',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Moutarde',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Tabasco',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Citron jaune',NULL);");

        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Salade',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Sorbet au citron vert',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Vinaigre balsamique',NULL);");

        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Comcombre',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Olive verte',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Bûche de chèvre',NULL);");

        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Pomme de terre',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Paprika',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Coriandre moulue',NULL);");

        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Purée de pomme de terre',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Herbe de provence',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Jaune d oeuf',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Blanc d oeur',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Fromage rapé',NULL);");

        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Jambon',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Haricots verts',NULL);");

        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Quinoa',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Menthe',NULL);");

        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Cuisse de poulet',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Epice à tajine',NULL);");

        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Tomate séchée',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Ras el Hanout',NULL);");
        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Cannelle',NULL);");

        database.execSQL("INSERT INTO `Ingredient`(`Nom`,`ID`) VALUES ('Spaghetti',NULL);");


    }

    public void initPlat(SQLiteDatabase database){
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Quiche Lardon Gruyère');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Foie de veau au Porto et au raisins secs');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Poulet au curry et aux oignons');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Osso Bucco de dinde');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Lasagnes');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Ratatouille');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Risotto aux champignons');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Salade melon mozzarella & avocat');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Salade cesar');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Salade d avocat au citron vert');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Salade de comcombre au chèvre et olives');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Salade de pomme de terre épicée');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Hachis parmentier');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Gratin d auphinois');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Gratin de courgettes');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Roulé de jambon aux haricots verts');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Salade de quinoa');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Tajine de poulet');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Tajine de légumes');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Spaghetti carbonara');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Carottes à la carbonara');");
        database.execSQL("INSERT INTO `Plat`(`ID`,`intitule`) VALUES (NULL,'Pâtes bolognaise');");
    }

    public void initTags(SQLiteDatabase database){
        database.execSQL("INSERT INTO `Tag`(`ID`,`Label`) VALUES (NULL,'Gourmand');");
        database.execSQL("INSERT INTO `Tag`(`ID`,`Label`) VALUES (NULL,'Elaboré');");
        database.execSQL("INSERT INTO `Tag`(`ID`,`Label`) VALUES (NULL,'Végétarien');");
        database.execSQL("INSERT INTO `Tag`(`ID`,`Label`) VALUES (NULL,'Salades');");
        database.execSQL("INSERT INTO `Tag`(`ID`,`Label`) VALUES (NULL,'Familial');");
        database.execSQL("INSERT INTO `Tag`(`ID`,`Label`) VALUES (NULL,'Gratin');");
        database.execSQL("INSERT INTO `Tag`(`ID`,`Label`) VALUES (NULL,'Healthy');");
        database.execSQL("INSERT INTO `Tag`(`ID`,`Label`) VALUES (NULL,'Exotique');");
        database.execSQL("INSERT INTO `Tag`(`ID`,`Label`) VALUES (NULL,'Variante');");
    }

    public void initPlatsTags(SQLiteDatabase database){
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (1,1);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (2,2);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (3,1);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (4,2);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (5,5);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (6,3);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (7,2);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (8,3);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (9,4);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (10,4);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (11,4);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (12,4);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (13,5);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (14,6);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (15,6);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (16,7);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (17,4);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (18,8);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (19,8);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (20,1);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (21,9);");
        database.execSQL("INSERT INTO `Plat_Tag`(`plat`,`tag`) VALUES (22,1);");
    }

    public void initPlatsIngredients(SQLiteDatabase database){
        //QUICHE LARDON GRUYERE
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (1,1,25);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (1,2,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (1,3,8);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (1,4,8);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (1,5,40);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (1,6,58);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (1,7,25);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (1,8,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (1,9,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (1,10,-1);");

        //FOIE DE VEAU
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (2,11,150);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (2,12,50);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (2,13,21);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (2,14,36);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (2,15,13);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (2,16,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (2,4,4);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (2,8,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (2,9,-1);");

        //POULET CURRY
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (3,17,110);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (3,18,70);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (3,3,250);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (3,19,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (3,20,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (3,21,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (3,8,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (3,9,-1);");

        //OSSO BUCCO DE DINDE
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (4,22,110);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (4,4,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (4,23,8);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (4,24,35);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (4,14,2);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (4,25,2);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (4,26,38);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (4,27,84);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (4,28,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (4,8,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (4,9,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (4,29,-1);");

        //LASAGNES
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (5,30,100);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (5,31,63);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (5,32,38);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (5,26,38);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (5,27,38);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (5,24,10);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (5,33,15);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (5,34,15);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (5,23,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (5,8,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (5,9,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (5,35,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (5,36,60);");

        //RATATOUILLE
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (6,38,88);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (6,39,88);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (6,40,88);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (6,24,88);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (6,27,130);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (6,28,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (6,23,22);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (6,41,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (6,42,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (6,8,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (6,9,-1);");

        //RISOTTO
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (7,43,50);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (7,44,75);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (7,3,125);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (7,45,15);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (7,24,35);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (7,28,10);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (7,8,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (7,9,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (7,46,38);");

        //MELON AVOCAT
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (8,47,300);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (8,48,150);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (8,36,40);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (8,49,15);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (8,50,50);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (8,51,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (8,52,20);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (8,23,23);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (8,8,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (8,9,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (8,53,-1);");

        //SALADE CESAR
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (9,54,150);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (9,46,13);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (9,55,12);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (9,23,58);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (9,46,3);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (9,56,3);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (9,57,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (9,58,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (9,59,120);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (9,28,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (9,8,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (9,9,-1);");

        //SALADE AVOCAT CITRON VERT
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (10,48,90);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (10,60,40);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (10,61,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (10,23,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (10,62,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (10,8,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (10,9,-1);");

        //SALADE DE COMCOMBRE AU CHEVRE ET OLIVE
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (11,63,150);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (11,64,35);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (11,65,70);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (11,23,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (11,62,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (11,35,-1);");

        //SALADE DE POMME DE TERRE EPICEE
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (12,66,130);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (12,23,7.5);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (12,59,60);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (12,51,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (12,67,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (12,68,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (12,20,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (12,8,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (12,9,-1);");

        //HACHIS PARMENTIER
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (13,31,100);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (13,69,75);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (13,24,65);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (13,28,40);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (13,27,60);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (13,4,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (13,71,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (13,46,8);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (13,15,8);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (13,7,13);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (13,8,-1);");
        database.execSQL("INSERT INTO `Plat_Ingredient`(`plat`,`ingredient`,`grammes`) VALUES (13,9,-1);");

        
    }
}
