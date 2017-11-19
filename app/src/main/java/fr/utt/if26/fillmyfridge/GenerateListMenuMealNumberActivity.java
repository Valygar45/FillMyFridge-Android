package fr.utt.if26.fillmyfridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;

public class GenerateListMenuMealNumberActivity extends AppCompatActivity implements View.OnClickListener {

    private Button continueButton;
    private Date from;
    private Date to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        from = new Date(getIntent().getLongExtra("from", 0));
        to = new Date(getIntent().getLongExtra("to", 0));

        int numberOfDays = daysBetween(from, to) + 1;
        Log.e("number of days:", numberOfDays + "");



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

    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
