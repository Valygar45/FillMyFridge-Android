package fr.utt.if26.fillmyfridge.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import fr.utt.if26.fillmyfridge.ListMenuDetailsActivity;
import fr.utt.if26.fillmyfridge.Objects.ListeMenus;
import fr.utt.if26.fillmyfridge.Objects.Menu;
import fr.utt.if26.fillmyfridge.Objects.Repas;
import fr.utt.if26.fillmyfridge.R;
import fr.utt.if26.fillmyfridge.RepasDetails;

/**
 * Created by alex2 on 21/11/2017.
 */

public class ListeMenuDetailsAdapter extends ArrayAdapter<Menu> {

    private ListeMenus listeMenus;
    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView dateLabel;
        LinearLayout linearLayout;
    }

    public ListeMenuDetailsAdapter(ListeMenus data, Context context) {
        super(context, R.layout.row_list_menu_details, data.getMenus());
        this.listeMenus = data;
        this.mContext=context;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Menu menu = listeMenus.getMenus().get(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ListeMenuDetailsAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


            viewHolder = new ListeMenuDetailsAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_list_menu_details, parent, false);
            viewHolder.dateLabel = (TextView) convertView.findViewById(R.id.TV_row_list_menu_details);
            viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.LL_listemenus_details);

            for(final Repas repas : menu.getRepas()){
                //ConstraintLayout viewRepas = (ConstraintLayout) convertView.findViewById(R.id.CS_liste_menu_details);
                Log.e("numberRepas", "idRepas:"+repas.getId());
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setPadding(30,8,8,30);

                Button button = new Button(getContext());
                button.setText("Voir");
                button.setBackgroundColor(ContextCompat.getColor(mContext, R.color.primaryLightColor));
                button.setPadding(8,0,8,0);
                button.setTextColor(ContextCompat.getColor(mContext, R.color.primaryTextColor));
                button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 100));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent datesIntent = new Intent(mContext, RepasDetails.class);
                        datesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        datesIntent.putExtra("repas", repas);
                        mContext.startActivity(datesIntent);
                    }
                });

                TextView txRepas = new TextView(getContext());
                txRepas.setText(repas.getNom());
                txRepas.setTextColor(ContextCompat.getColor(mContext, R.color.primaryLightColor));
                txRepas.setPadding(30,8,50,8);

                linearLayout.addView(txRepas);
                linearLayout.addView(button);
                viewHolder.linearLayout.addView(linearLayout);
            }

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ListeMenuDetailsAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.dateLabel.setText("Le " + menu.getDateFourDigits() + ": ");
        Log.e("numberRepas", "listeMenu:"+listeMenus.getMenus().size());
        Log.e("numberRepas", "idMenu:"+menu.getId());


        // Return the completed view to render on screen
        return convertView;
    }
}