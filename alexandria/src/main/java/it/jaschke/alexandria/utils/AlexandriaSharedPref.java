package it.jaschke.alexandria.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by AdonisArifi on 18.1.2016 - 2016 . alexandria
 */
public class AlexandriaSharedPref {

    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferencesDefault;
    private Context myContext;
    public static AlexandriaSharedPref alexandriaSharedPrefInstance;

    public static AlexandriaSharedPref getAlexandriaSharedPrefInstance(Context context) {
        if (alexandriaSharedPrefInstance == null) {
            alexandriaSharedPrefInstance = new AlexandriaSharedPref(context);
        }
        return alexandriaSharedPrefInstance;
    }

    public AlexandriaSharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences(SupportMethod.getPackageNameOfAplication(), Context.MODE_PRIVATE);
        sharedPreferencesDefault = PreferenceManager.getDefaultSharedPreferences(context);
        myContext = context;
    }

    public String getSelectStartScreen() {
        return sharedPreferencesDefault.getString("pref_startFragment", "0");
    }
}
