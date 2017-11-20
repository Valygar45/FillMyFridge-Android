package fr.utt.if26.fillmyfridge.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import fr.utt.if26.fillmyfridge.Objects.ListeMenus;
import fr.utt.if26.fillmyfridge.Objects.Menu;
import fr.utt.if26.fillmyfridge.R;

/**
 * Created by valygar on 20/11/2017.
 */

public class MealNumberAdapter extends ArrayAdapter<Menu>{

    private ListeMenus listeMenus;
    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView dateLabel;
        SeekBar SBDate;
    }

    public MealNumberAdapter(ListeMenus data, Context context) {
        super(context, R.layout.row_meal_number, data.getMenus());
        this.listeMenus = data;
        this.mContext=context;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Menu Menu = listeMenus.getMenus().get(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_meal_number, parent, false);
            viewHolder.dateLabel = (TextView) convertView.findViewById(R.id.TV_meal_number_row_label);
            viewHolder.SBDate = (SeekBar) convertView.findViewById(R.id.SB_meal_number_row);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.dateLabel.setText("Le " + Menu.getDateFourDigits() + ": ");
        // Return the completed view to render on screen
        return convertView;
    }
}