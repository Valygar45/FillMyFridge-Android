package fr.utt.if26.fillmyfridge.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.thomashaertel.widget.MultiSpinner;

import java.util.ArrayList;

import fr.utt.if26.fillmyfridge.GenerateListMenuMealThemeActivity;
import fr.utt.if26.fillmyfridge.Objects.Menu;
import fr.utt.if26.fillmyfridge.Objects.Plat;
import fr.utt.if26.fillmyfridge.Objects.Repas;
import fr.utt.if26.fillmyfridge.Objects.Tag;
import fr.utt.if26.fillmyfridge.R;

/**
 * Created by alex2 on 20/11/2017.
 */

public class ThemeAdapter extends ArrayAdapter<Repas> {
    private Menu menu;
    private Context mContext;
    private MultiSpinner spinner;
    private ArrayAdapter<String> adapter;
    private Context activityContext;

    // View lookup cache
    private static class ViewHolder {
        TextView dateLabel;

    }

    public ThemeAdapter(Menu data, Context context, Context activityContext) {
        super(context, R.layout.row_gen_theme, data.getRepas());
        this.menu = data;
        this.mContext=context;
        this.activityContext = activityContext;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Repas repas = menu.getRepas().get(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ThemeAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ThemeAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_gen_theme, parent, false);
            viewHolder.dateLabel = (TextView) convertView.findViewById(R.id.TV_meal_theme_row_label);
            /*adapter = new ArrayAdapter<String>(activityContext, android.R.layout.simple_spinner_item);
            adapter.add("Item1");
            adapter.add("Item2");
            adapter.add("Item3");
            adapter.add("Item4");
            adapter.add("Item5");


            MultiSpinner.MultiSpinnerListener onSelectedListener = new MultiSpinner.MultiSpinnerListener() {
                public void onItemsSelected(boolean[] selected) {
                    // Do something here with the selected items
                }
            };
            // get spinner and set adapter
            spinner = (MultiSpinner) convertView.findViewById(R.id.spinnerMulti);
            spinner.setAdapter(adapter, false, onSelectedListener);

            // set initial selection
            boolean[] selectedItems = new boolean[adapter.getCount()];
            selectedItems[1] = true; // select second item
            spinner.setSelected(selectedItems);*/

            Spinner spinner = (Spinner) convertView.findViewById(R.id.theme_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                    R.array.theme_spinner, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    // Your code here
                    String tagString = adapterView.getItemAtPosition(position).toString();
                    ArrayList<Plat> plats = new ArrayList<Plat>();
                    ArrayList<Tag> tags = new ArrayList<Tag>();
                    int[] tagIds = mContext.getResources().getIntArray(R.array.theme_spinner_id);
                    tags.add(new Tag(tagIds[position], tagString));
                    plats.add(new Plat(0,"Plat", null, tags));
                    repas.setPlats(plats);
                    repas.setId(1);
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ThemeAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.dateLabel.setText("Repas n°"+repas.getNumero());
        // Return the completed view to render on screen
        return convertView;
    }
}
