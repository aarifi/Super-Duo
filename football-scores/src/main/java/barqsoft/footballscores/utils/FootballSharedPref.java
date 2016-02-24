package barqsoft.footballscores.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by AdonisArifi on 18.1.2016 - 2016 . alexandria
 */
public class FootballSharedPref {

    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferencesDefault;
    private Context myContext;
    public static FootballSharedPref footballSharedPrefInstance;

    public static FootballSharedPref getAlexandriaSharedPrefInstance(Context context) {
        if (footballSharedPrefInstance == null) {
            footballSharedPrefInstance = new FootballSharedPref(context);
        }
        return footballSharedPrefInstance;
    }

    public FootballSharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences(SupportMethod.getSupportMethodInstance(context).getPackageApp(), Context.MODE_PRIVATE);
        sharedPreferencesDefault = PreferenceManager.getDefaultSharedPreferences(context);
        myContext = context;
    }

    public void saveLeagueSelected(String leagueId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.LEAGUE_SELECTED, Integer.parseInt(leagueId));
        editor.apply();
    }

    public int getLeagueSelected() {
        return sharedPreferences.getInt(Constants.LEAGUE_SELECTED, 0);
    }

}
