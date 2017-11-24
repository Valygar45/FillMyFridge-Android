package fr.utt.if26.fillmyfridge.Objects;

import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import fr.utt.if26.fillmyfridge.Dao.ListeMenusDAO;

/**
 * Created by alex2 on 15/11/2017.
 */

public class ListeMenus implements Serializable{
    private int id;
    private Date dateDebut;
    private Date dateFin;
    private ArrayList<Menu> menus;

    public ListeMenus(int id, Date dateDebut, Date dateFin, ArrayList<Menu> menus) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.menus = menus;
    }

    public ListeMenus(Date dateDebut, Date dateFin, ArrayList<Menu> menus) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.menus = menus;
    }

    public ListeMenus(Date dateDebut, Date dateFin) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        /*return "ListeMenus{" +
                "id=" + id +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", menus=" + menus +
                '}';*/
        return "Menu"+this.getDateFourDigits();
    }

    public int getNumberOfMeals(){
        return (int)(((this.getDateDebut().getTime() - this.getDateFin().getTime()) / (1000 * 60 * 60 * 24)) + 1);
    }

    public String getDateFourDigits(){
        String fourDigitsDateDebut = new SimpleDateFormat("dd/MM/yy").format(this.getDateDebut());
        String fourDigitsDateFin = new SimpleDateFormat("dd/MM/yy").format(this.getDateFin());
        return " du " + fourDigitsDateDebut+" au "+fourDigitsDateFin;
    }

    public ArrayList<ItemListeDeCourse> getIngredients(){
        ArrayList<ItemListeDeCourse> listeDeCourses = new ArrayList<ItemListeDeCourse>();
        for(Menu m : this.getMenus()){
            for(Repas r : m.getRepas()){
                for(Plat p : r.getPlats()){
                    for(Ingredient i : p.getIngredients()){
                        ItemListeDeCourse ildc = new ItemListeDeCourse(i, r.getNumberofPersonnes());
                        int pos = this.isInAL(listeDeCourses, ildc);
                        if(pos >= 0){
                            listeDeCourses.get(pos).setNbFois(listeDeCourses.get(pos).getNbFois() + ildc.getNbFois());
                        }else{
                            listeDeCourses.add(ildc);
                        }
                    }
                }
            }
        }

        return listeDeCourses;
    }

    public int isInAL(ArrayList<ItemListeDeCourse> alToCheck, ItemListeDeCourse ildc){
        Iterator<ItemListeDeCourse> it = alToCheck.iterator();
        int posFound = 0;
        while(it.hasNext()){
            ItemListeDeCourse ildcTemp = it.next();
            if(ildc.getIngredient().equals(ildcTemp.getIngredient())){
                Log.e("EQUAL", ildc.toString() + " -- " + ildcTemp.toString());
                return posFound;
            }
            posFound++;
        }
        return -1;
    }
}
