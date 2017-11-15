package fr.utt.if26.fillmyfridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GenerateListMenuDatesActivity extends AppCompatActivity implements View.OnClickListener {

    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_list_menu_dates);

        continueButton = (Button) findViewById(R.id.gen_menu_list_dates_continue);
        continueButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.gen_menu_list_dates_continue:
                Intent mealNumberIntent = new Intent(GenerateListMenuDatesActivity.this,
                        GenerateListMenuMealNumberActivity.class);
                GenerateListMenuDatesActivity.this.startActivity(mealNumberIntent);
                break;
        }
    }
}
