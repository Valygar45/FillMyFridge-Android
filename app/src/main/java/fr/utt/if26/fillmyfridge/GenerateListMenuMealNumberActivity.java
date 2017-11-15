package fr.utt.if26.fillmyfridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GenerateListMenuMealNumberActivity extends AppCompatActivity implements View.OnClickListener {

    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_list_menu_meal_number);

        continueButton = (Button) findViewById(R.id.gen_menu_list_meal_number_continue);
        continueButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.gen_menu_list_meal_number_continue:
                Intent PPMIntent = new Intent(GenerateListMenuMealNumberActivity.this, GenerateListMenuPersonPerMealActivity.class);
                GenerateListMenuMealNumberActivity.this.startActivity(PPMIntent);
                break;
        }
    }
}
