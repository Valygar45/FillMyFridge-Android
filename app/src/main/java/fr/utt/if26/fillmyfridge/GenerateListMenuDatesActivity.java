package fr.utt.if26.fillmyfridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import org.joda.time.LocalDate;

import java.util.Date;

import fr.utt.if26.fillmyfridge.Objects.ListeMenus;

public class GenerateListMenuDatesActivity extends AppCompatActivity implements View.OnClickListener {

    private Button continueButton;
    private CalendarView dateDebutView;
    private CalendarView dateFinView;
    private Date dateDebut;
    private Date dateFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_list_menu_dates);

        continueButton = (Button) findViewById(R.id.gen_menu_list_dates_continue);
        continueButton.setOnClickListener(this);

        dateDebutView = (CalendarView) findViewById(R.id.gen_menu_list_from);
        dateFinView = (CalendarView) findViewById(R.id.gen_menu_list_to);
        dateDebutView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {

                dateDebut = (new LocalDate(i, i1+1, i2)).toDate();
            }
        });

        dateFinView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                dateFin = (new LocalDate(i, i1+1, i2)).toDate();

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.gen_menu_list_dates_continue:

                ListeMenus listeMenus = new ListeMenus(this.dateDebut, this.dateFin);

                Intent mealNumberIntent = new Intent(GenerateListMenuDatesActivity.this,
                        GenerateListMenuMealNumberActivity.class);
                mealNumberIntent.putExtra("listeMenus", listeMenus);
                GenerateListMenuDatesActivity.this.startActivity(mealNumberIntent);
                break;
        }
    }
}
