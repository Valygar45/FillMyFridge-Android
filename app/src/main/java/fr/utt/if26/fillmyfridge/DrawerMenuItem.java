package fr.utt.if26.fillmyfridge;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@Layout(R.layout.drawer_item)
public class DrawerMenuItem {

    public static final int DRAWER_MENU_GENERER_MENUS = 1;
    public static final int DRAWER_MENU_VOIR_LISTES_MENUS = 2;
    public static final int DRAWER_MENU_A_PROPOS = 3;
    private int mMenuPosition;
    private Context mContext;
    private DrawerCallBack mCallBack;

    @View(R.id.itemNameTxt)
    private TextView itemNameTxt;


    public DrawerMenuItem(Context context, int menuPosition) {
        mContext = context;
        mMenuPosition = menuPosition;
    }

    @Resolve
    private void onResolved() {
        switch (mMenuPosition){
            case DRAWER_MENU_GENERER_MENUS:
                itemNameTxt.setText("Générer une nouvelle liste");
                //itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_account_circle_black_18dp));
                break;
            case DRAWER_MENU_VOIR_LISTES_MENUS:
                itemNameTxt.setText("Voir les listes");
                //itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_compare_arrows_black_18dp));
                break;
            case DRAWER_MENU_A_PROPOS:
                itemNameTxt.setText("A propos");
                //itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_group_work_black_18dp));
                break;

        }
    }

    @Click(R.id.mainView)
    private void onMenuItemClick(){
        switch (mMenuPosition){
            case DRAWER_MENU_GENERER_MENUS:
                if(mCallBack != null)mCallBack.onProfileMenuSelected();
                Intent generateIntent = new Intent(mContext, GenerateListMenuDatesActivity.class);
                generateIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(generateIntent);
                break;
            case DRAWER_MENU_VOIR_LISTES_MENUS:
                if(mCallBack != null)mCallBack.onRequestMenuSelected();
                Intent listeMenusIntent = new Intent(mContext, AllListMenuActivity.class);
                listeMenusIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(listeMenusIntent);
                break;
            case DRAWER_MENU_A_PROPOS:
                Toast.makeText(mContext, "A PROPOS", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onGroupsMenuSelected();
                Intent aProposIntent = new Intent(mContext, AProposActivity.class);
                aProposIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(aProposIntent);
                break;
        }
    }

    public void setDrawerCallBack(DrawerCallBack callBack) {
        mCallBack = callBack;
    }

    public interface DrawerCallBack{
        void onProfileMenuSelected();
        void onRequestMenuSelected();
        void onGroupsMenuSelected();
    }
}

