package fr.utt.if26.fillmyfridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.utt.if26.fillmyfridge.Objects.ListeMenus;

public class GenerateListMenuEnding extends AppCompatActivity implements View.OnClickListener {

    private Button goHomeButton;
    private Button seeListButton;
    private ListeMenus listeMenus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_list_menu_ending);

        goHomeButton = (Button) findViewById(R.id.gen_lm_ending_go_home);
        goHomeButton.setOnClickListener(this);

        seeListButton = (Button) findViewById(R.id.gen_lm_ending_liste_course);
        seeListButton.setOnClickListener(this);

        Intent intent = getIntent();

        listeMenus = (ListeMenus) intent.getSerializableExtra("listeMenus");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.gen_lm_ending_go_home:
                Intent goHomeIntent = new Intent(GenerateListMenuEnding.this, MainActivity.class);
                GenerateListMenuEnding.this.startActivity(goHomeIntent);
                break;
            case R.id.gen_lm_ending_liste_course:
                Intent seeListIntent = new Intent(GenerateListMenuEnding.this, ListeDeCourseActivity.class);
                seeListIntent.putExtra("listeMenus", listeMenus);
                GenerateListMenuEnding.this.startActivity(seeListIntent);
                break;
        }
    }
}
