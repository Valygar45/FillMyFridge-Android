package fr.utt.if26.fillmyfridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GenerateListMenuMealThemeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_list_menu_meal_theme);

        continueButton = (Button) findViewById(R.id.gen_menu_list_meal_theme_continue);
        continueButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.gen_menu_list_meal_theme_continue:
                Intent datesIntent = new Intent(GenerateListMenuMealThemeActivity.this, GenerateListMenuEnding.class);
                GenerateListMenuMealThemeActivity.this.startActivity(datesIntent);
                break;
        }
    }
}
