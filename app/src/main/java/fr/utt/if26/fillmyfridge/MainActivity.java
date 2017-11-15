package fr.utt.if26.fillmyfridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button generateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateButton = (Button) findViewById(R.id.home_gen_menu_list);
        generateButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.home_gen_menu_list:
                Intent datesIntent = new Intent(MainActivity.this, GenerateListMenuDatesActivity.class);
                MainActivity.this.startActivity(datesIntent);
                break;
        }
    }
}
