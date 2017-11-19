package fr.utt.if26.fillmyfridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.joda.time.LocalDate;

import java.util.Date;

import fr.utt.if26.fillmyfridge.Objects.ListeMenus;

public class GenerateListMenuMealNumberActivity extends AppCompatActivity implements View.OnClickListener {

    private Button continueButton;
    private ListeMenus listeMenus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        listeMenus = (ListeMenus) intent.getSerializableExtra("listeMenus");

        Log.e("serializable", listeMenus.toString());

        for (LocalDate date = new LocalDate(listeMenus.getDateDebut()); date.isBefore(new LocalDate(listeMenus.getDateFin())); date = date.plusDays(1))
        {

        }


        setContentView(R.layout.activity_generate_list_menu_meal_number);

        continueButton = (Button) findViewById(R.id.gen_menu_list_meal_number_continue);
        continueButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.gen_menu_list_meal_number_continue:

                Intent PPMIntent = new Intent(GenerateListMenuMealNumberActivity.this,
                        GenerateListMenuPersonPerMealActivity.class);
                GenerateListMenuMealNumberActivity.this.startActivity(PPMIntent);
                break;
        }
    }

}
