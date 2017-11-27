package fr.utt.if26.fillmyfridge.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import fr.utt.if26.fillmyfridge.Objects.ListeMenus;
import fr.utt.if26.fillmyfridge.Objects.Menu;
import fr.utt.if26.fillmyfridge.Objects.Repas;
import fr.utt.if26.fillmyfridge.R;

/**
 * Created by alex2 on 20/11/2017.
 */

public class PersonNumberAdapter extends ArrayAdapter<Repas> {
    private Menu menu;
    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView dateLabel;
        SeekBar SBDate;
    }

    public PersonNumberAdapter(Menu data, Context context) {
        super(context, R.layout.row_meal_number, data.getRepas());
        this.menu = data;
        this.mContext=context;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Repas repas = menu.getRepas().get(position);
        repas.setNumberofPersonnes(1);
        // Check if an existing view is being reused, otherwise inflate the view
        PersonNumberAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new PersonNumberAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_meal_number, parent, false);
            viewHolder.dateLabel = (TextView) convertView.findViewById(R.id.TV_meal_number_row_label);
            viewHolder.SBDate = (SeekBar) convertView.findViewById(R.id.SB_meal_number_row);
            final TextView currentValue = (TextView) convertView.findViewById(R.id.TV_meal_number_current);

            viewHolder.SBDate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    currentValue.setText(String.valueOf(seekBar.getProgress()+1));

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    int nbPerson = seekBar.getProgress()+1;
                    repas.setNumberofPersonnes(nbPerson);
                }
            });

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (PersonNumberAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.dateLabel.setText("Repas n°"+repas.getNumero());
        // Return the completed view to render on screen
        return convertView;
    }
}
